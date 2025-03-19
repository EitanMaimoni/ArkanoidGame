package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.ScoreTrackingListener;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import objects.Ball;
import collision.BallRemover;
import objects.Block;
import collision.BlockRemover;
import objects.Paddle;
import objects.ScoreIndicator;
import physics.Collidable;
import physics.GameEnvironment;
import physics.Velocity;
import sprites.Sprite;
import sprites.SpriteCollection;
import utils.Counter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;

/**
 * The GameLevel class represents a level in a simple game. It contains sprites
 * and a game environment.
 * Each level has a collection of sprites and a game environment, and manages
 * the game logic and animations.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023 -06-01
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final Counter score;
    private final LevelInformation levelInfo;
    private boolean running;

    /**
     * Constructs a new GameLevel with the specified level information, animation
     * runner, keyboard sensor, and score.
     *
     * @param levelInfo the level information
     * @param runner    the animation runner
     * @param keyboard  the keyboard sensor
     * @param score     the score counter
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner runner, KeyboardSensor keyboard, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.runner = runner;
        this.levelInfo = levelInfo;
        this.keyboard = keyboard;
        this.score = score;
    }

    /**
     * Returns the Counter object representing the number of remaining blocks.
     *
     * @return the Counter object representing the number of remaining blocks
     */
    public Counter getBlocksCounter() {
        return this.remainingBlocks;
    }

    /**
     * Returns the Counter object representing the number of remaining balls.
     *
     * @return the Counter object representing the number of remaining balls
     */
    public Counter getBallsCounter() {
        return this.remainingBalls;
    }

    /**
     * Returns the Counter object representing the current score.
     *
     * @return the Counter object representing the current score
     */
    public Counter getScoreCounter() {
        return this.score;
    }

    /**
     * Adds a new collidable to the game environment.
     *
     * @param c the collidable object to add to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * removes a new collidable to the game environment.
     *
     * @param c the collidable object to add to the game environment.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a new sprite to the game.
     *
     * @param s the sprite to add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removes a new sprite to the game.
     *
     * @param s the sprite to add to the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the game by creating and adding all the necessary game elements
     * according to level info given (borders, blocks, balls, and paddle).
     */
    public void initialize() {
        // death border
        Block death = new Block(new Rectangle(new Point(25, 0), 750, 25), Color.gray);
        BallRemover z = new BallRemover(this, getBallsCounter());
        death.addHitListener(z);
        death.addToGame(this);
        // borders
        Block[] borderBlocks = new Block[] {
                new Block(new Rectangle(new Point(0, 575), 25, 600), Color.gray),
                new Block(new Rectangle(new Point(25, 575), 750, 25), Color.gray),
                new Block(new Rectangle(new Point(775, 575), 25, 600), Color.gray)
        };
        for (Block block : borderBlocks) {
            block.addToGame(this);
        }
        // blocks
        BlockRemover y = new BlockRemover(this, getBlocksCounter());
        ScoreTrackingListener w = new ScoreTrackingListener(getScoreCounter());
        List<Block> levelBlocks = levelInfo.blocks();
        for (Block block : levelBlocks) {
            block.addHitListener(y);
            block.addHitListener(w);
            block.addToGame(this);
        }
        getBlocksCounter().increase(levelInfo.numberOfBlocksToRemove());
        // paddle
        Paddle paddle = new Paddle(keyboard, levelInfo.paddleSpeed(), levelInfo.paddleWidth());
        paddle.addToGame(this);
        // score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, 350, 600, levelInfo.levelName());
        sprites.addSprite(scoreIndicator);
    }

    /**
     * Create balls on top of paddle according to level info given.
     */
    public void createBallsOnTopOfPaddle() {
        List<Ball> balls = new ArrayList<>();
        List<Velocity> velocities = levelInfo.initialBallVelocities();
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            balls.add(new Ball(400, 50, 6, Color.white));
            balls.get(i).setVelocity(velocities.get(i));
            balls.get(i).setEnvironment(this.environment);
            balls.get(i).addToGame(this);
            getBallsCounter().increase(1);
        }
    }

    /**
     * Runs the game - starts the animation loop.
     */
    public void run() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(1.5, 3, this.sprites, levelInfo.getBackground()));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        levelInfo.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        Animation pauseScreen = new PauseScreen();
        Animation a1k = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pauseScreen);
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P") || this.keyboard.isPressed("פ")) {
            this.runner.run(a1k);
        }
        if (remainingBlocks.getValue() == 0) {
            getScoreCounter().increase(100);
            this.running = false;
        }
        if (remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }
}
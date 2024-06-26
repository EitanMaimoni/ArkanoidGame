import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The Game class represents a simple game. The game consists of sprites
 * and a game environment.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter score;
    private GUI gui;
    private Sleeper sleeper;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    private static final int BLOCK_HEIGHT = 25;
    private static final int BLOCK_WIDTH = 50;
    /**
     * Constructs a new Game with an empty sprite collection
     * and game environment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
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
     * Initializes the game by creating and adding all the necessary game
     * elements (borders, blocks, balls and paddle).
     */
    public void initialize() {
        this.gui = new GUI("Game", WINDOW_WIDTH, WINDOW_HEIGHT);
        this.sleeper = new Sleeper();
        //death border
        Block death = new Block(new Rectangle(new Point(25, 0), 750, 25), Color.gray);
        BallRemover z = new BallRemover(this, getBallsCounter());
        death.addHitListener(z);
        death.addToGame(this);
        //borders
        Block[] blocks = new Block[] {
                new Block(new Rectangle(new Point(0, 575), 25, 600), Color.gray),
                new Block(new Rectangle(new Point(25, 575), 750, 25), Color.gray),
                new Block(new Rectangle(new Point(775, 575), 25, 600), Color.gray)
        };
        for (Block block : blocks) {
            block.addToGame(this);
        }
        //blocks
        BlockRemover y = new BlockRemover(this, getBlocksCounter());
        ScoreTrackingListener w = new ScoreTrackingListener(getScoreCounter());
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.cyan, Color.pink, Color.green};
        for (int i = 12; i > 6; i--) {
            for (int j = i; j > 0; j--) {
                Point p = new Point(775 - (BLOCK_WIDTH * j), 200 + (i * BLOCK_HEIGHT));
                Rectangle r = new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(r, colors[12 - i]);
                block.addHitListener(y);
                block.addHitListener(w);
                getBlocksCounter().increase(1);
                block.addToGame(this);
            }
        }
        //balls
        Ball[] balls = new Ball[] {
                new Ball(400, 50, 6, Color.white),
                new Ball(400, 50, 6, Color.white),
                new Ball(400, 50, 6, Color.white)
        };
        balls[0].setVelocity(-3, 3);
        balls[1].setVelocity(0, 4);
        balls[2].setVelocity(3, 3);
        for (Ball ball : balls) {
            ball.setEnvironment(this.environment);
            ball.addToGame(this);
            getBallsCounter().increase(1);
        }
        //paddle
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard);
        paddle.addToGame(this);
        //score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, 350, 600);
        sprites.addSprite(scoreIndicator);
    }
    /**
     * Runs the game - starts the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (remainingBlocks.getValue() == 0) {
                getScoreCounter().increase(100);
                gui.close();
                return;
            }
            if (remainingBalls.getValue() == 0) {
                gui.close();
                return;
            }
        }
    }
}
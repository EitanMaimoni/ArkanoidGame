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
 * @since 2023-05-04
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    private static final int BLOCK_HEIGHT = 25;
    private static final int BLOCK_WIDTH = 50;
    /**
     * Instantiates a new Game with an empty sprite collection
     * and game environment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
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
     * Adds a new sprite to the game.
     *
     * @param s the sprite to add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * Initializes the game by creating and adding all the necessary game
     * elements (borders, blocks, balls and paddle).
     */
    public void initialize() {
        this.gui = new GUI("Game", WINDOW_WIDTH, WINDOW_HEIGHT);
        this.sleeper = new Sleeper();
        //borders
        Block[] blocks = new Block[] {
                new Block(new Rectangle(new Point(0, 25), 800, 25), Color.gray),
                new Block(new Rectangle(new Point(0, 600), 25, 600), Color.gray),
                new Block(new Rectangle(new Point(25, 600), 750, 25), Color.gray),
                new Block(new Rectangle(new Point(775, 600), 25, 600), Color.gray)
        };
        for (Block block : blocks) {
            block.addToGame(this);
        }
        //blocks
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.cyan, Color.pink, Color.green};
        for (int i = 12; i > 6; i--) {
            for (int j = i; j > 0; j--) {
                Point p = new Point(775 - (BLOCK_WIDTH * j), 200 + (i * BLOCK_HEIGHT));
                Rectangle r = new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(r, colors[12 - i]);
                block.addToGame(this);
            }
        }
        //balls
        Ball b1 = new Ball(455, 100, 6, Color.white);
        b1.setEnvironment(this.environment);
        b1.setVelocity(-3, 3);
        Ball b2 = new Ball(445, 100, 6, Color.white);
        b2.setEnvironment(this.environment);
        b2.setVelocity(3, 3);
        b1.addToGame(this);
        b2.addToGame(this);
        //paddle
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard);
        paddle.addToGame(this);
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
        }
    }
}
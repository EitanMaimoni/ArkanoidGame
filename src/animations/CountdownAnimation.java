package animations;

import biuoop.DrawSurface;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation class represents an animation that displays a
 * countdown on the screen.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class CountdownAnimation implements Animation {
    private final double numOfSecondsPerPrint;
    private int countFrom;
    private double startTime;
    private final Sprite backGround;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;

    /**
     * Constructs a new CountdownAnimation.
     *
     * @param numOfSeconds The total number of seconds for the countdown.
     * @param countFrom    The starting count value.
     * @param gameScreen   The SpriteCollection representing the game screen.
     * @param backGround   The background Sprite of the countdown animation.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite backGround) {
        this.backGround = backGround;
        this.gameScreen = gameScreen;
        this.countFrom = countFrom;
        this.numOfSecondsPerPrint = numOfSeconds / countFrom;
        this.startTime = System.currentTimeMillis();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.backGround.drawOn(d);
        this.gameScreen.drawAllOn(d);
        // Calculate elapsed time
        double elapsedTime = (System.currentTimeMillis() - startTime);
        if (elapsedTime / 1000 >= numOfSecondsPerPrint) {
            countFrom--;
            this.startTime = System.currentTimeMillis();
        }
        // Draw the countdown number
        d.setColor(Color.white);
        if (countFrom <= 0) {
            d.drawText(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, "Go", 32);
        } else {
            d.drawText(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, Integer.toString(countFrom), 32);
        }
        // Check if the countdown is finished
        if (countFrom <= -1) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
package objects;

import biuoop.DrawSurface;
import sprites.Sprite;
import utils.Counter;

import java.awt.Color;

/**
 * The ScoreIndicator class represents a visual indicator for the current score
 * in the game.
 * It implements the Sprite interface to be able to draw itself on a
 * DrawSurface.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class ScoreIndicator implements Sprite {
    private final Counter scoresCounter;
    private final int positionX;
    private final int positionY;
    private final String levelName;

    /**
     * Constructs a new ScoreIndicator.
     *
     * @param scoresCounter the Counter object representing the current score
     * @param positionX     the x-coordinate of the indicator's position
     * @param positionY     the y-coordinate of the indicator's position
     * @param levelName     the name of the current level we are playing
     */
    public ScoreIndicator(Counter scoresCounter, int positionX, int positionY, String levelName) {
        this.scoresCounter = scoresCounter;
        this.positionX = positionX;
        this.positionY = positionY;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 600 - positionY, 800, 25);
        // Get the current score and convert it to a string
        int score = scoresCounter.getValue();
        String scoreText = "Score: " + score;
        // Draw the score on the DrawSurface at the specified position
        d.setColor(Color.black);
        d.drawText(positionX, 600 - (positionY - 20), scoreText, 15);
        String levelText = "Level Name: " + this.levelName;
        // Draw the score on the DrawSurface at the specified position
        d.setColor(Color.black);
        d.drawText(600, 600 - (positionY - 20), levelText, 15);
    }

    @Override
    public void timePassed() {
    }
}

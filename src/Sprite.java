import biuoop.DrawSurface;

/**
 * The Sprite interface represents an object in the game.
 * Every sprite can be drawn on the screen, and its state can change
 * as the game progresses.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-05-04
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that a unit of time has passed in the game.
     */
    void timePassed();
}
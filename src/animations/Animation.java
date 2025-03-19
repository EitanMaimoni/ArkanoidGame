package animations;

import biuoop.DrawSurface;

/**
 * The Animation interface represents a single animation in a game.
 * It provides methods for performing one frame of the animation and determining
 * if the animation should stop.
 * 
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public interface Animation {
    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
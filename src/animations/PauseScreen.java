package animations;

import biuoop.DrawSurface;

/**
 * The PauseScreen class represents a screen displayed when the game is paused.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class PauseScreen implements Animation {
    private final boolean stop;

    /**
     * Constructs a new PauseScreen animation.
     *
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(175, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
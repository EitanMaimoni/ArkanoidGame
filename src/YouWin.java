import biuoop.DrawSurface;

/**
 * The YouWin class represents an animation displayed when the player wins the game.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class YouWin implements Animation {
    private final Counter scoresCounter;
    private final boolean stop;
    /**
     * Constructs a new YouWin animation.
     *
     * @param score the score
     */
    public YouWin(Counter score) {
        this.stop = false;
        this.scoresCounter = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(210, d.getHeight() / 2, "You Win! Your score is " + scoresCounter.getValue(), 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
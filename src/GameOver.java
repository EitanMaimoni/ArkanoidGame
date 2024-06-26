import biuoop.DrawSurface;

/**
 * The GameOver class represents an animation displayed when the player lost the game.
 *
 * @author Eitan Maimoni 
 * @version 19.0.2
 * @since 2023 -06-01
 */
public class GameOver implements Animation {
    private final Counter scoresCounter;
    private final boolean stop;
    /**
     * Constructs a new GameOver animation.
     *
     * @param score The score counter.
     */
    public GameOver(Counter score) {
        this.stop = false;
        this.scoresCounter = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(210, d.getHeight() / 2, "Game Over. Your score is " + scoresCounter.getValue(), 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
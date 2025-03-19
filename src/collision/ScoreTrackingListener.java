package collision;

import objects.Ball;
import objects.Block;
import utils.Counter;

/**
 * The ScoreTrackingListener class is responsible for tracking and updating the
 * score when a block is hit.
 * It implements the HitListener interface to listen for hit events and perform
 * the necessary actions.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener.
     *
     * @param scoreCounter the Counter object representing the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
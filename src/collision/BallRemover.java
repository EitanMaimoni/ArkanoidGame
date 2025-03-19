package collision;

import game.GameLevel;
import objects.Ball;
import objects.Block;
import utils.Counter;

/**
 * The BallRemover class is responsible for removing balls from the game and
 * updating the ball counter.
 * It implements the HitListener interface to listen for hit events and perform
 * the necessary actions.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Constructs a new Block remover.
     *
     * @param gameLevel    the game
     * @param removedBalls the removed blocks
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getRectangle().getUpperLeftPoint().getY() == 0) {
            hitter.removeFromGame(gameLevel);
            remainingBalls.decrease(1);
        }
    }
}
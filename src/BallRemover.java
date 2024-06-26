/**
 * The BallRemover class is responsible for removing balls from the game and updating the ball counter.
 * It implements the HitListener interface to listen for hit events and perform the necessary actions.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;
    /**
     * Constructs a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BallRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBalls = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getRectangle().getUpperLeftPoint().getY() == 0) {
            hitter.removeFromGame(game);
            remainingBalls.decrease(1);
        }
    }
}
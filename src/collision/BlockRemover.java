package collision;

import game.GameLevel;
import objects.Ball;
import objects.Block;
import utils.Counter;

/**
 * The BlockRemover class is responsible for removing blocks from the game and
 * updating the counter
 * of remaining blocks.
 * This class implements the HitListener interface and defines the behavior when
 * a block is hit.
 * It removes the block from the game and decreases the counter of remaining
 * blocks.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover.
     *
     * @param gameLevel     the game from which blocks will be removed
     * @param removedBlocks the counter of remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}
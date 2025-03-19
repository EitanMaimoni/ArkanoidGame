package levels;

import java.util.List;

import objects.Block;
import physics.Velocity;
import sprites.Sprite;

/**
 * The LevelInformation interface represents information about a game level.
 * It provides methods to retrieve details such as the number of balls, initial
 * ball velocities, paddle speed and width,
 * level name, background sprite, blocks configuration, and the number of blocks
 * to remove.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns the initial velocities of the balls.
     *
     * @return a list of ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background sprite for the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns a list of blocks that make up the level.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed to clear the level.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import backgrounds.NightBackground;
import geometry.Point;
import geometry.Rectangle;
import objects.Block;
import physics.Velocity;
import sprites.Sprite;

/**
 * The DirectHit class represents the "Direct Hit" level in a game.
 * 
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class DirectHit implements LevelInformation {
    private final int ballsNum = 1;
    private final int blockNum = 1;
    private static final double BLOCK_WIDTH = 50;
    private static final double BLOCK_HEIGHT = 50;

    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(90, 3.5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new NightBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point p = new Point(375, 500);
        Rectangle r = new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT);
        Block block = new Block(r, Color.red);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blockNum;
    }
}

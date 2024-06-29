import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Green3 class represents the "Green 3" level in a game.
 *
 * @author Eitan Maimoni 
 * @version 19.0.2
 * @since 2023 -06-01
 */
public class Green3 implements LevelInformation {
    private final int ballsNum = 2;
    private final int blockNum = 57;
    private static final double BLOCK_WIDTH = 50;
    private static final double BLOCK_HEIGHT = 25;
    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < this.ballsNum; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(60 + i * 60, 5));
        }
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 8;
    }
    @Override
    public int paddleWidth() {
        return 110;
    }
    @Override
    public String levelName() {
        return "Green 3";
    }
    @Override
    public Sprite getBackground() {
        return new DayBackground();
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.blue, Color.red, Color.yellow, Color.cyan, Color.pink, Color.white};
        for (int i = 12; i > 6; i--) {
            for (int j = i; j > 0; j--) {
                Point p = new Point(775 - (BLOCK_WIDTH * j), 200 + (i * BLOCK_HEIGHT));
                Rectangle r = new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(r, colors[12 - i]);
                blocks.add(block);
            }
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blockNum;
    }
}

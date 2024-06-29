import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The WideEasy class represents the "Wide Easy" level in a game.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class WideEasy implements LevelInformation {
    private final int ballsNum = 10;
    private final int blockNum = 15;
    private static final double BLOCK_WIDTH = 50;
    private static final double BLOCK_HEIGHT = 25;
    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 1; i <= this.ballsNum; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(35 + i * 10, 3.5));
        }
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 3;
    }
    @Override
    public int paddleWidth() {
        return 600;
    }
    @Override
    public String levelName() {
        return "Wide Ease";
    }
    @Override
    public Sprite getBackground() {
        return new CityBackground();
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.cyan};
        int colorIndex = 0;
        int nextColorFlag = 1;
        for (int i = 0; i < blockNum; i++) {
            Point p = new Point(25 + (BLOCK_WIDTH * i), 400);
            Rectangle r = new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(r, colors[colorIndex]);
            blocks.add(block);
            if ((nextColorFlag) % 3 == 0) {
                colorIndex = colorIndex + 1;
                nextColorFlag = 1;
                continue;
            }
            if (colors[colorIndex].equals(Color.green)) {
                nextColorFlag = nextColorFlag + 1;
            } else {
                nextColorFlag = nextColorFlag + 2;
            }
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blockNum;
    }
}

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The NightBackground class represents the night background in a game.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class NightBackground implements Sprite {
    private static final double WINDOW_WIDTH = 800;
    private static final double WINDOW_HEIGHT = 600;
    @Override
    public void drawOn(DrawSurface d) {
        // Draw the sky
        d.setColor(new Color(0, 0, 139)); // Dark blue
        d.fillRectangle(0, 0, (int) WINDOW_WIDTH, (int) WINDOW_HEIGHT);
        // Draw the water
        d.setColor(new Color(25, 25, 95)); // Midnight blue
        d.fillRectangle(0, (int) (WINDOW_HEIGHT * 0.5), (int) WINDOW_WIDTH, (int) (WINDOW_HEIGHT * 0.3));
        // Draw the sand
        d.setColor(new Color(139, 69, 19)); // Sandy brown
        d.fillRectangle(0, (int) (WINDOW_HEIGHT * 0.7), (int) WINDOW_WIDTH, (int) (WINDOW_HEIGHT * 0.3));
        // Draw the moon
        int moonCenterX = (int) (WINDOW_WIDTH * 0.2);
        int moonCenterY = (int) (WINDOW_HEIGHT * 0.2);
        int moonRadius = 50;
        Color halationColor1 = new Color(200, 200, 200);    // Whiter shade
        Color halationColor2 = new Color(215, 220, 215);    // Whiter shade
        Color halationColor3 = new Color(230, 230, 230);    // Whiter shade
        d.setColor(halationColor1);
        d.fillCircle(moonCenterX, moonCenterY, moonRadius + 15);
        d.setColor(halationColor2);
        d.fillCircle(moonCenterX, moonCenterY, moonRadius + 10);
        d.setColor(halationColor3);
        d.fillCircle(moonCenterX, moonCenterY, moonRadius + 5);
        d.setColor(Color.WHITE);
        d.fillCircle(moonCenterX, moonCenterY, moonRadius);
        // Draw the tree trunk
        d.setColor(new Color(100, 69, 19)); // Brown
        d.fillRectangle((int) (WINDOW_WIDTH * 0.2), (int) (WINDOW_HEIGHT * 0.5), 20, (int) (WINDOW_HEIGHT * 0.3));
        // Draw the tree top
        d.setColor(new Color(0, 100, 0)); // Dark green
        int treeTopCenterX = (int) (WINDOW_WIDTH * 0.2) + 10;
        int treeTopCenterY = (int) (WINDOW_HEIGHT * 0.5);
        d.fillCircle(treeTopCenterX, treeTopCenterY, 60);
        d.fillCircle(treeTopCenterX - 40, treeTopCenterY, 50);
        d.fillCircle(treeTopCenterX + 40, treeTopCenterY, 50);
        d.fillCircle(treeTopCenterX, treeTopCenterY - 40, 50);
        d.fillCircle(treeTopCenterX, treeTopCenterY + 40, 50);
    }
    @Override
    public void timePassed() {
    }
}

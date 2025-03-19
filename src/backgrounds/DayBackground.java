package backgrounds;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * The DayBackground class represents the day background in a game.
 * 
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class DayBackground implements Sprite {
    private static final double WINDOW_WIDTH = 800;
    private static final double WINDOW_HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the sky
        d.setColor(new Color(135, 206, 250)); // Light blue
        d.fillRectangle(0, 0, (int) WINDOW_WIDTH, (int) WINDOW_HEIGHT);
        // Draw the water
        d.setColor(new Color(0, 119, 190)); // Deep blue
        d.fillRectangle(0, (int) (WINDOW_HEIGHT * 0.5), (int) WINDOW_WIDTH, (int) (WINDOW_HEIGHT * 0.3));
        // Draw the sand
        d.setColor(new Color(244, 164, 96)); // Sandy brown
        d.fillRectangle(0, (int) (WINDOW_HEIGHT * 0.7), (int) WINDOW_WIDTH, (int) (WINDOW_HEIGHT * 0.3));
        // Draw the sun
        d.setColor(Color.YELLOW);
        d.fillCircle((int) (WINDOW_WIDTH * 0.8), (int) (WINDOW_HEIGHT * 0.2), 50);
        // Draw the sun rays
        d.setColor(new Color(255, 255, 153)); // Pale yellow
        drawSunRays(d, (int) (WINDOW_WIDTH * 0.8), (int) (WINDOW_HEIGHT * 0.2), 50, 60);
        // Draw the tree trunk
        d.setColor(new Color(139, 69, 19)); // Brown
        d.fillRectangle((int) (WINDOW_WIDTH * 0.2), (int) (WINDOW_HEIGHT * 0.5), 20, (int) (WINDOW_HEIGHT * 0.3));
        // Draw the tree top
        d.setColor(new Color(34, 139, 34)); // Green
        int treeTopCenterX = (int) (WINDOW_WIDTH * 0.2) + 10;
        int treeTopCenterY = (int) (WINDOW_HEIGHT * 0.5);
        d.fillCircle(treeTopCenterX, treeTopCenterY, 60);
        d.fillCircle(treeTopCenterX - 40, treeTopCenterY, 50);
        d.fillCircle(treeTopCenterX + 40, treeTopCenterY, 50);
        d.fillCircle(treeTopCenterX, treeTopCenterY - 40, 50);
        d.fillCircle(treeTopCenterX, treeTopCenterY + 40, 50);
    }

    private void drawSunRays(DrawSurface d, int centerX, int centerY, int radius, int numRays) {
        double angle = 2 * Math.PI / numRays;
        for (int i = 0; i < numRays; i++) {
            double rayStartX = centerX + radius * Math.cos(i * angle);
            double rayStartY = centerY + radius * Math.sin(i * angle);
            double rayEndX = centerX + radius * 1.5 * Math.cos(i * angle);
            double rayEndY = centerY + radius * 1.5 * Math.sin(i * angle);
            d.drawLine((int) rayStartX, (int) rayStartY, (int) rayEndX, (int) rayEndY);
        }
    }

    @Override
    public void timePassed() {
    }
}

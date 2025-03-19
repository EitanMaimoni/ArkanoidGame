package backgrounds;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * The CityBackground class represents the city background in a game.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class CityBackground implements Sprite {
    private static final double WINDOW_WIDTH = 800;
    private static final double WINDOW_HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the sky
        d.setColor(new Color(200, 127, 80));
        d.fillRectangle(0, 0, (int) WINDOW_WIDTH, (int) WINDOW_HEIGHT);
        // Draw the sun (partially hidden behind the big building)
        int sunX = 370;
        int sunY = 300;
        int sunRadius = 70;
        d.setColor(new Color(255, 215, 0));
        d.fillCircle(sunX, sunY, sunRadius);
        // Draw the big building
        int bigBuildingX = 75;
        int bigBuildingY = (int) (WINDOW_HEIGHT - 320);
        int bigBuildingWidth = 310;
        int bigBuildingHeight = 320;
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(bigBuildingX, bigBuildingY, bigBuildingWidth, bigBuildingHeight);
        // Draw the small building
        int smallBuildingX = 550;
        int smallBuildingY = (int) (WINDOW_HEIGHT - 250);
        int smallBuildingWidth = 130;
        int smallBuildingHeight = 250;
        d.fillRectangle(smallBuildingX, smallBuildingY, smallBuildingWidth, smallBuildingHeight);
        // Draw the antenna on the small building
        int antennaHeight = 80;
        int antennaWidth = 8;
        int antennaX = smallBuildingX + (smallBuildingWidth / 2) - (antennaWidth / 2);
        int antennaY = smallBuildingY - antennaHeight;
        int circleRadius = 20;
        int circleX = antennaX + 4;
        int circleY = antennaY;
        d.setColor(Color.darkGray);
        d.fillCircle(circleX, circleY, circleRadius);
        d.setColor(Color.gray);
        d.fillRectangle(antennaX, antennaY, antennaWidth, antennaHeight);
        d.setColor(Color.red);
        d.fillCircle(circleX, circleY, circleRadius - 12);
        d.setColor(Color.white);
        d.fillCircle(circleX, circleY, circleRadius - 15);
        // Draw the windows for both buildings
        d.setColor(new Color(245, 222, 179));
        drawWindows(d, bigBuildingX, bigBuildingY, bigBuildingWidth, bigBuildingHeight);
        drawWindows(d, smallBuildingX, smallBuildingY, smallBuildingWidth, smallBuildingHeight);
        // Draw the clouds
        d.setColor(new Color(190, 190, 190));
        drawCloud(d, 100, 100);
        drawCloud(d, 300, 150);
        drawCloud(d, 600, 100);
    }

    private void drawWindows(DrawSurface d, int x, int y, int width, int height) {
        int windowSize = 20;
        int padding = 10;
        int windowStartX = x + padding;
        int windowStartY = y + padding;
        while (windowStartY + windowSize <= y + height - padding) {
            while (windowStartX + windowSize <= x + width - padding) {
                d.fillRectangle(windowStartX, windowStartY, windowSize, windowSize);
                windowStartX += windowSize + padding;
            }
            windowStartX = x + padding;
            windowStartY += windowSize + padding;
        }
    }

    private void drawCloud(DrawSurface d, int x, int y) {
        int cloudWidth = 120;
        int cloudHeight = 60;
        d.fillOval(x, y, cloudWidth, cloudHeight);
        d.fillOval(x + cloudWidth / 4, y - cloudHeight / 4, cloudWidth, cloudHeight);
        d.fillOval(x + cloudWidth / 2, y, cloudWidth, cloudHeight);
    }

    @Override
    public void timePassed() {
    }
}
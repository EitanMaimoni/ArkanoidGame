import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * A program that generates random lines and draws
 * them on a GUI window.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-04-20
 */

public class AbstractArtDrawing {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int LINE_NUM = 10;
    private static final int RADIUS = 3;
    /**
     * Generates a random line.
     *
     * @return a Line object with random start and end points.
     */
    public Line generateRandomLine() {
        // create a random-number generator
        Random rand = new Random();
        // get integer in range 1-400
        int x1 = rand.nextInt(WIDTH) + 1;
        int x2 = rand.nextInt(WIDTH) + 1;
        // get integer in range 1-300
        int y1 = rand.nextInt(HEIGHT) + 1;
        int y2 = rand.nextInt(HEIGHT) + 1;
        return new Line(x1, y1, x2, y2);
    }
    /**
     * Draws a line on the given DrawSurface object.
     *
     * @param l the Line object to be drawn.
     * @param d the DrawSurface object to draw on.
     */
    void drawLine(Line l, DrawSurface d) {
        int x1, y1, x2, y2;
        x1 = (int) l.start().getX();
        y1 = (int) l.start().getY();
        x2 = (int) l.end().getX();
        y2 = (int) l.end().getY();

        d.drawLine(x1, y1, x2, y2);
    }
    /**
     * Draws 10 random lines on a GUI window and
     * circles at the midpoint and intersection points.
     */
    public void drawRandomLines() {
        // Create a window with the title "Random Lines Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Example", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] array = new Line[LINE_NUM];
        for (int i = 0; i < LINE_NUM; i++) {
            Line line = generateRandomLine();
            int midPointX = (int) line.middle().getX();
            int midPointY = (int) line.middle().getY();
            // Draw line and midpoint circle
            d.setColor(Color.BLACK);
            drawLine(line, d);
            d.setColor(Color.BLUE);
            d.fillCircle(midPointX, midPointY, RADIUS);
            array[i] = line;
        }
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = i; j < LINE_NUM; j++) {
                if (array[j] == null) {
                    continue;
                }
                // Find intersection and draw circle
                Point temp = array[i].intersectionWith(array[j]);
                if (temp != null) {
                    int x = (int) temp.getX();
                    int y = (int) temp.getY();
                    d.setColor(Color.RED);
                    d.fillCircle(x, y, RADIUS);
                }
            }
        }
        gui.show(d);
    }
    /**
     * The main method that creates an instance of
     * AbstractArtDrawing and calls drawRandomLines method.
     *
     * @param args command-line arguments, not used in this program
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}

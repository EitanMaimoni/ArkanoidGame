import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * A class for creating a multiple frames bouncing balls animation.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-04-20
 */

public class MultipleFramesBouncingBallsAnimation {
    private static final int MAX_RADIUS = 50;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private static final int FIRST_START_BORDER = 50;
    private static final int FIRST_END_BORDER = 500;
    private static final int SECOND_START_BORDER = 450;
    private static final int SECOND_END_BORDER = 600;
    /**
     * Determines whether two colors are similar based on their RGB values.
     *
     * @param color1 the first color
     * @param color2 the second color
     * @return true if the colors are similar, false otherwise
     */
    private static boolean isSimilarColor(Color color1, Color color2) {
        // calculate the difference between each color component
        int redDiff, greenDiff, blueDiff;
        redDiff = Math.abs(color1.getRed() - color2.getRed());
        greenDiff = Math.abs(color1.getGreen() - color2.getGreen());
        blueDiff = Math.abs(color1.getBlue() - color2.getBlue());
        // return true if the differences are all less than 50
        return redDiff < 50 && greenDiff < 50 && blueDiff < 50;
    }

    /**
     * Ensures that the given radius value is within the allowed range.
     *
     * @param r     the radius
     * @param range the range of values that the radius can be in
     * @return the corrected radius value
     */
    private static int radiosCheck(int r, int range) {
        if (r >= range / 2) {
            return (range / 2 - 1);
        }
        if (r < 0) {
            return 1;
        }
        return r;
    }
    /**
     * creates a random speed (bigger speed for bigger radius) for the ball.
     *
     * @param r the radius of the ball
     * @return the speed value
     */
    private static int createRandomSpeed(int r) {
        if (r >= MAX_RADIUS) {
            return 1;
        } else {
            // set the speed for balls with radius that small than 50.
            // [(61 - 1) / 9] is not specific number, it just gives
            // speed between 2 and 10.
            return (61 - r) / 6;
        }
    }
    /**
     * Creates a new ball with a random position, velocity, and color.
     *
     * @param r     the radius of the ball
     * @param min   the minimum position value for the ball
     * @param range the range of position values that the ball can be in
     * @return the new ball object
     */
    private static Ball createRandomBall(int r, int min, int range) {
        Random rand = new Random();
        // Checks if the radius is within the range.
        r = radiosCheck(r, range);
        // Calculate random coordinates, angle and speed.
        // get integer in the range of possible location for the ball
        int x1 = rand.nextInt(range - r * 2) + r + min + 1;
        int y1 = rand.nextInt(range - r * 2) + r + min + 1;
        int angle = rand.nextInt(360) + 1;
        int speed = createRandomSpeed(r);
        // Create new point and velocity using the calculated values.
        Point center = new Point(x1, y1);
        Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
        // generate a random color for the ball that is not gray or yellow
        Color color;
        do {
            color = Color.getHSBColor(rand.nextFloat(), 1, 1);
        } while (isSimilarColor(color, Color.GRAY)
                || isSimilarColor(color, Color.YELLOW));
        // Create new ball using the calculated values.
        Ball ball = new Ball(center, r, color);
        ball.setVelocity(velocity);
        ball.setBorders(min, (min + range), min, (min + range));
        return ball;
    }
    /**
     * Draws an animation of bouncing balls on a GUI window.
     *
     * @param balls the array of Ball objects to be animated
     */
    private static void drawAnimation(Ball[] balls) {
        // Create a window with the title "Bouncing Ball Animation"
        // which is 700 pixels wide and 700 pixels high.
        GUI gui = new GUI("Multiple Frame Bouncing Balls Animation",
                WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        int n = balls.length;
        // while loop that draws the balls
        while (true) {
            // Get the draw surface of the GUI
            DrawSurface d = gui.getDrawSurface();
            // Set the background color to white and draw a gray rectangle
            d.setColor(java.awt.Color.WHITE);
            d.fillRectangle(0, 0, WIDTH, HEIGHT);
            d.setColor(Color.GRAY);
            int min = FIRST_START_BORDER;
            int range = FIRST_END_BORDER - FIRST_START_BORDER;
            d.fillRectangle(min, min, range, range);
            // Loop through the first half of the balls and move/draw them
            for (int i = 0; i < n; i++) {
                if (i + 1 <= n / 2) {
                    balls[i].moveOneStep();
                    balls[i].drawOn(d);
                }
            }
            // Draw a yellow rectangle
            d.setColor(Color.YELLOW);
            min = SECOND_START_BORDER;
            range = SECOND_END_BORDER - SECOND_START_BORDER;
            d.fillRectangle(min, min, range, range);
            // Loop through the second half of the balls and move/draw them
            for (int i = 0; i < n; i++) {
                if (i + 1 > n / 2) {
                    balls[i].moveOneStep();
                    balls[i].drawOn(d);
                }
            }
            // Show the GUI and sleep for 30 milliseconds
            gui.show(d);
            sleeper.sleepFor(30);
        }
    }
    /**
     * The entry point of the application.
     *
     * @param args the command line arguments
     *            (integer values representing the radius of the balls)
     */
    public static void main(String[] args) {
        try {
            int n = args.length;
            Ball[] balls = new Ball[n];
            // Loop through the radius and create new Ball with random positions
            for (int i = 0; i < n; i++) {
                double radius = Double.parseDouble(args[i]);
                // create the first and second half of the balls
                if (i + 1 <= n / 2) {
                    int min = FIRST_START_BORDER;
                    int range = FIRST_END_BORDER - FIRST_START_BORDER;
                    balls[i] = createRandomBall((int) radius, min, range);
                } else {
                    int min = SECOND_START_BORDER;
                    int range = SECOND_END_BORDER - SECOND_START_BORDER;
                    balls[i] = createRandomBall((int) radius, min, range);
                }
            }
            // Start the animation
            drawAnimation(balls);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input."
                    + " Please re-enter your input using numbers.");
        }
    }
}


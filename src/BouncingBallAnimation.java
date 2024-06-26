import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * A class for creating a bouncing balls animation.
 *
 * @author Eitan Maimoni 
 * @version 19.0.2
 * @since 2023-04-20
 */

public class BouncingBallAnimation  {
    private static final double EPSILON = 1e-10;
    private static final double MAX_SPEED = 15;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int BIGGER_BORDER = 200;
    private static final int SMALLER_BORDER = 0;
    /**
     * Setting the new position for a ball that has gone out of the border.
     *
     * @param z The current position of the ball.
     * @param r The radius of the ball.
     * @return The new position of the ball.
     */
    private static double outOfBorder(double z, double r) {
        double zBiggerBorder, zSmallerBorder;
        zBiggerBorder =  BIGGER_BORDER;
        zSmallerBorder = SMALLER_BORDER;
        // checks if the ball gone out of border. if it out of border, setting
        // new position for the ball.
        if (EPSILON > zBiggerBorder - (z + r)
                || EPSILON > (z - r) - zSmallerBorder) {
            z = (zBiggerBorder - zSmallerBorder) / 2;
        }
        return z;
    }
    /**
     * Checks if the ball speed is too high, if so reduce his speed.
     *
     * @param a The current speed of the ball.
     * @return The new speed of the ball.
     */
    private static double speedLimit(double a) {
        if (a > MAX_SPEED) {
            return MAX_SPEED;
        } else if (a < -MAX_SPEED) {
            return -MAX_SPEED;
        }
        return a;
    }
    /**
     * Draws the animation of a bouncing ball.
     *
     * @param start The starting point of the ball.
     * @param dx The x-axis speed of the ball.
     * @param dy The y-axis speed of the ball.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        // Create a window with the title "Bouncing Ball Animation"
        // which is 200 pixels wide and 200 pixels high.
        GUI gui = new GUI("Bouncing Ball Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        double x, y;
        int r;
        x = start.getX();
        y = start.getY();
        r = 30;
        x = outOfBorder(x, r);
        y = outOfBorder(y, r);
        // creating the ball
        Ball ball = new Ball(x, y, r, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        // while loop that draws the ball
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            // Show the GUI and sleep for 30 milliseconds
            gui.show(d);
            sleeper.sleepFor(30);
        }
    }
    /**
     * The main function that gets the ball's starting point and velocity
     * and starts the animation.
     *
     * @param args The command line arguments containing the ball's
     *             starting point and velocity.
     */
    public static void main(String[] args) {
        try {
            if (args.length < 4) {
                System.out.println("Insufficient data to generate a ball.");
                return;
            }
            double x, y, dx, dy;
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);
            dx = Double.parseDouble(args[2]);
            dx = speedLimit(dx);
            dy = Double.parseDouble(args[3]);
            dy = speedLimit(dy);
            Point point = new Point(x, y);
            drawAnimation(point, dx, dy);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input."
                    + " Please re-enter your input using numbers.");
        }
    }
}

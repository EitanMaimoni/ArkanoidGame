import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

/**
 * A class for creating a multiple bouncing balls animation.
 *
 * @author Eitan Maimoni 
 * @version 19.0.2
 * @since 2023-04-20
 */

public class MultipleBouncingBallsAnimation {
    private static final int MAX_RADIUS = 50;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int END_BORDER = 200;
    private static final int START_BORDER = 0;
    /**
     * A helper method to check if radios is in possible range.
     *
     * @param r the given radius.
     * @param range the range in which the ball can be placed.
     * @return the radius that can be used.
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
     * @param r the radius of the ball.
     * @return a random speed for the ball.
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
     * A helper method to create a random ball.
     *
     * @param r the radius of the ball.
     * @return a new random ball.
     */
    private static Ball createRandomBall(int r) {
        Random rand = new Random();
        int range = END_BORDER - START_BORDER;
        // Checks if the radius is within the range.
        r = radiosCheck(r, range);
        // Calculate random coordinates, angle and speed.
        // get integer in the range of possible location for the ball
        int x1 = rand.nextInt(range - r * 2) + r + START_BORDER + 1;
        int y1 = rand.nextInt(range - r * 2) + r + START_BORDER + 1;
        int angle = rand.nextInt(360) + 1;
        int speed = createRandomSpeed(r);
        // Create new ball using the calculated values.
        Point center = new Point(x1, y1);
        Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
        Ball ball = new Ball(center, r, java.awt.Color.BLACK);
        ball.setVelocity(velocity);
        return ball;
    }
    /**
     * Draw the animation of the bouncing balls.
     *
     * @param balls the balls to be drawn.
     */
    private static void drawAnimation(Ball[] balls) {
        // Create a window with the title "Bouncing Ball Animation"
        // which is 200 pixels wide and 200 pixels high.
        GUI gui = new GUI("Multiple Bouncing Balls Animation",
                WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        // while loop that draws the balls
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(java.awt.Color.WHITE);
            d.fillRectangle(START_BORDER, START_BORDER, END_BORDER, END_BORDER);
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            // Show the GUI and sleep for 30 milliseconds
            gui.show(d);
            sleeper.sleepFor(30);
        }
    }
    /**
     * The main entry point of the application.
     *
     * @param args the radius of the balls.
     */
    public static void main(String[] args) {
        try {
            int n = args.length;
            Ball[] balls = new Ball[n];
            // Create a ball for each given radius.
            for (int i = 0; i < n; i++) {
                double radios = Double.parseDouble(args[i]);
                balls[i] = createRandomBall((int) radios);
            }
            drawAnimation(balls);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input."
                    + " Please re-enter your input using numbers.");
        }
    }
}

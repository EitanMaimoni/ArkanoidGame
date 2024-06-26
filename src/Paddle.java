import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Paddle class represents the player-controlled paddle
 * in the game in 2D space.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Paddle implements Sprite, Collidable, KeyboardSensor  {

    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private final java.awt.Color color;
    private final int speed;
    private final int width;
    private static final double EPSILON = 1e-10;
    private static final int Y_AXIS_FIX = 600;
    private static final int BORDER_WIDTH = 24;
    private static final int WINDOW_WIDTH = 800;
    private static final double HEIGHT = 15;
    /**
     * Constructs a new Paddle object with a specified keyboard sensor.
     *
     * @param keyboard The keyboard sensor to use for
     * @param speed The speed of the paddle
     * @param width The width of the paddle
     * controlling the paddle's movement.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int speed, int width) {
        double x = (400 - (width * 0.5));
        Point point = new Point(x, 15);
        this.rect = new Rectangle(point, width, HEIGHT);
        this.color = Color.orange;
        this.keyboard = keyboard;
        this.speed = speed;
        this.width = width;
    }
    /**
     * Returns true if the specified key is currently
     * being pressed on the keyboard, false otherwise.
     *
     * @param var1 The key to check.
     * @return True if the key is currently being pressed, false otherwise.
     */
    public boolean isPressed(String var1) {
        return keyboard.isPressed(var1);
    }
    /**
     * Returns true if a double value equal to another double value.
     *
     * @param a The first double value.
     * @param b The second double value.
     * @return True if a = b, false otherwise.
     */
    public boolean isDoubleEqual(double a, double b) {
        if (Math.abs(a - b) < EPSILON) {
            return true;
        }
        return false;
    }
    /**
     * Moves the paddle one step to the left.
     */
    public void moveLeft() {
        double newX = this.rect.getUpperLeftPoint().getX() - this.speed;
        if (isDoubleEqual(newX, BORDER_WIDTH) || newX < BORDER_WIDTH) {
            return;
        }
        Point point = new Point(this.rect.getUpperLeftPoint(), -this.speed, 0);
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        this.rect = new Rectangle(point, width, height);
    }
    /**
     * Moves the paddle one step to the right.
     */
    public void moveRight() {
        double newX = this.rect.getUpperLeftPoint().getX() + this.width + this.speed;
        if (isDoubleEqual(newX, WINDOW_WIDTH - BORDER_WIDTH)
                || newX > WINDOW_WIDTH - BORDER_WIDTH) {
            return;
        }
        Point point = new Point(this.rect.getUpperLeftPoint(), this.speed, 0);
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        this.rect = new Rectangle(point, width, height);
    }
    /**
     * Determines which region of the paddle the ball collides with.
     *
     * @param collisionPoint the point of collision between the ball and paddle
     * @return an integer representing the region of the paddle,
     * where 1 is the leftmost region and 5 is the rightmost region
     */
    public int whichRegion(Point collisionPoint) {
        double collisionX = collisionPoint.getX();
        double upperLeftX  = this.rect.getUpperLeftPoint().getX();
        double regionWidth = rect.getWidth() / 5;
        for (int i = 1; i < 5; i++) {
            if (collisionX < upperLeftX  + (i * regionWidth)) {
                return i;
            }
        }
        return 5;
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }
    @Override
    public void drawOn(DrawSurface d) {
        Point point = this.rect.getUpperLeftPoint();
        int h = (int) this.rect.getHeight();
        int w = (int) this.rect.getWidth();
        int x = (int) point.getX();
        int y = (int) point.getY();
        // draw the ball as a filled circle with the given color
        // *600 - y because the original board y-axis is opposite
        d.setColor(this.color);
        d.fillRectangle(x, Y_AXIS_FIX - y, w, h);
        d.setColor(Color.black);
        d.drawRectangle(x, Y_AXIS_FIX - y, w, h);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // add the "magic number" on this method are not actually "magic number"
        // those number are specific number we have been asked to use
        // in the assigment.
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        double speed = Math.sqrt(dx * dx + dy * dy);
        // if the ball hit in the upper line
        if (this.rect.getUpperLine().isPointOnLine(collisionPoint)) {
            int region = whichRegion(collisionPoint);
            // change the velocity based on the region of the paddle the ball
            // hit on (parameters as asked in the assigment)
            if (region == 1) {
                // if the paddle close to the border, its make sure the ball
                // doesn't go out of the border
                if (this.rect.getLeftLine().start().getX() < 35) {
                    return Velocity.fromAngleAndSpeed(80, speed);
                }
                return Velocity.fromAngleAndSpeed(150, speed);
            } else if (region == 2) {
                return Velocity.fromAngleAndSpeed(120, speed);
            } else if (region == 3) {
                return new Velocity(dx, -dy);
            } else if (region == 4) {
                return Velocity.fromAngleAndSpeed(60, speed);
            } else {
                // if the paddle close to the border, its make sure the ball
                // doesn't go out of the border
                if (this.rect.getRightLine().start().getX() > 765) {
                    return Velocity.fromAngleAndSpeed(100, speed);
                }
                return Velocity.fromAngleAndSpeed(30, speed);
            }
        }
        // if the ball hit in the left line
        if (this.rect.getLeftLine().isPointOnLine(collisionPoint)) {
            return new Velocity(-dx, dy);
        } else if (this.rect.getRightLine().isPointOnLine(collisionPoint)) {
            // if the ball hit in the right line
            return new Velocity(-dx, dy);
        }
        return new Velocity(dx, dy);
    }
    /**
     * This method adds the paddle to the game by adding it
     * as both a sprite and a collidable.
     * @param g the game to add the paddle to.
     * */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * This method moves the paddle one step according to the user input,
     * by calling the 'moveLeft' or 'moveRight' methods accordingly.
     * */
    public void moveOneStep() {
        if (isPressed(LEFT_KEY)) {
            moveLeft();
        }
        if (isPressed(RIGHT_KEY)) {
            moveRight();
        }
    }
    @Override
    public String className() {
        return "Paddle";
    }
}
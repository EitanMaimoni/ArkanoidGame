import biuoop.DrawSurface;

/**
 * The Ball class represents a 2D ball object
 * with a position, radius, color, and velocity.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-04-20
 */
public class Ball {
    private Point point;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity = null;
    private int xLeftBorder = 0;
    private int xRightBorder = 200;
    private int yBottomBorder = 0;
    private int yTopBorder = 200;
    //200 and 0 are the default borders
    private static final double EPSILON = 1e-10;
    /**
     * Constructs a new Ball with the given position, radius, and color.
     *
     * @param point  the position of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point point, int radius, java.awt.Color color) {
        this.point = point;
        this.radius = radius;
        this.color = color;
    }
    /**
     * Constructs a new Ball with the given coordinates, radius, and color.
     *
     * @param x      the x coordinate of the ball's center
     * @param y      the y coordinate of the ball's center
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this.point = new Point(x, y);
        this.radius = radius;
        this.color = color;
    }
    /**
     * Sets the borders for this Rectangle object.
     *
     * @param xLeft the leftmost x-coordinate of the rectangle
     * @param xRight the rightmost x-coordinate of the rectangle
     * @param yBottom the bottommost y-coordinate of the rectangle
     * @param yTop the topmost y-coordinate of the rectangle
     */
    public void setBorders(int xLeft, int xRight, int yBottom, int yTop) {
        this.xLeftBorder = xLeft;
        this.xRightBorder = xRight;
        this.yTopBorder = yTop;
        this.yBottomBorder = yBottom;
    }
    /**
     * Returns the x coordinate of the center of the ball.
     *
     * @return the x coordinate of the center of the ball
     */
    public int getX() {
        return (int) this.point.getX();
    }
    /**
     * Returns the y coordinate of the center of the ball.
     *
     * @return the y coordinate of the center of the ball
     */
    public int getY() {
        return (int) this.point.getY();
    }
    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return (int) this.radius;
    }
    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * Sets the velocity of the ball given its x and y components.
     *
     * @param dx the x component of the velocity
     * @param dy the y component of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * Draws the ball on the given surface.
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        int x = this.getX();
        int y = this.getY();
        int r = this.getSize();
        // draw the ball as a filled circle with the given color
        surface.setColor(this.getColor());
        surface.fillCircle(x, y, r);
    }
    /**
     * Move one step within the given boundaries.
     * If the velocity of the ball is null, the ball will not move.
     * The ball's position is updated based on its current velocity,
     * and the velocity is updated if the ball hits a boundary.
     */
    public void moveOneStep() {
        if (velocity == null) {
            return;
        }
        // Get the current position and velocity of the ball
        double x1, y1, r, dx, dy;
        x1 = this.getX();
        y1 = this.getY();
        r = this.radius;
        dx = velocity.getVelocityX();
        dy = velocity.getVelocityY();
        // Check if the ball hits a horizontal boundary
        // and update the x velocity accordingly
        if (EPSILON > xRightBorder - (x1 + r + dx)
                || EPSILON > (x1 - r + dx) - xLeftBorder) {
            dx = -dx;
        }
        // Check if the ball hits a vertical boundary
        // and update the y velocity accordingly
        if (EPSILON > yTopBorder - (y1 + r + dy)
                || EPSILON > (y1 - r + dy) - yBottomBorder) {
            dy = -dy;
        }
        // Update the velocity and position of the ball
        this.velocity = new Velocity(dx, dy);
        this.point = this.getVelocity().applyToPoint(this.point);
    }
}


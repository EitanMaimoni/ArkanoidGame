/**
 * The Velocity class specifies the change in
 * position on the `x` and the `y` axes.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Velocity {
    // The change in position on the x-axis.
    private final double dx;
    // The change in position on the y-axis.
    private final double dy;
    /**
     * Constructs a new Velocity object.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Creates a new Velocity object from an angle and speed.
     *
     * @param angleDeg the angle in degrees
     * @param speed the speed
     * @return the new Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angleDeg, double speed) {
        double angleInRadians = Math.toRadians(angleDeg);
        double dx = speed * Math.cos(angleInRadians);
        double dy = speed * Math.sin(angleInRadians);
        return new Velocity(dx, dy);
    }
    /**
     * Applies the current velocity to a point and returns a new point.
     *
     * @param p the point to apply the velocity to
     * @return a new point with the velocity applied to it
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.getDX();
        double y = p.getY() + this.getDY();
        return new Point(x, y);
    }
    /**
     * Gets the change in position on the x-axis.
     *
     * @return the change in position on the x-axis
     */
    public double getDX() {
        return this.dx;
    }
    /**
     * Gets the change in position on the y-axis.
     *
     * @return the change in position on the y-axis
     */
    public double getDY() {
        return this.dy;
    }
}

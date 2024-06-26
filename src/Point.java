/**
 * The point class represents a point in a 2D coordinate system.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-05-04
 */
public class Point {
    private final double x;
    private final double y;
    /**
     * Constructs a new Point with the given x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Constructs a new point by adding the specified x and y offsets
     * to an existing point.
     *
     * @param point the existing point to add the offsets to
     * @param x the x offset to add
     * @param y the y offset to add
     */
    public Point(Point point, double x, double y) {
        this.x = point.getX() + x;
        this.y = point.getY() + y;
    }
    /**
     * Computes and returns the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    /**
     * Determines whether two double values are equal
     * within a small epsilon value.
     *
     * @param a the first double value to compare
     * @param b the second double value to compare
     * @return true if the values are equal\, false otherwise
     */
    public boolean isDoubleEqual(double a, double b) {
        return Math.abs(a - b) < 1e-10;
    }
    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point
     * @return true if this point is equal to the other point, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return isDoubleEqual(this.x, other.x) && isDoubleEqual(this.y, other.y);
    }
    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }
    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}
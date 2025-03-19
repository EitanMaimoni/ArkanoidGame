package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Rectangle` class represents a rectangle in 2D space.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Rectangle {
    private Line upperLine;
    private Line lowerLine;
    private Line leftLine;
    private Line rightLine;
    private final Point upperLeftPoint;
    private final double width;
    private final double height;

    /**
     * Constructs a new rectangle with the specified upper-left point,
     * width, and height.
     *
     * @param upperLeftPoint the upper-left point of the rectangle.
     * @param width          the width of the rectangle.
     * @param height         the height of the rectangle.
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeftPoint, double width, double height) {
        this.upperLeftPoint = upperLeftPoint;
        this.width = width;
        this.height = height;
        initializeLines();
    }

    /**
     * Initializes the four lines of the rectangle using its upper-left point,
     * width and height.
     */
    public void initializeLines() {
        Point upperLeft, upperRight, lowerLeft, lowerRight;
        upperLeft = this.upperLeftPoint;
        upperRight = new Point(this.upperLeftPoint, width, 0);
        lowerLeft = new Point(this.upperLeftPoint, 0, -height);
        lowerRight = new Point(this.upperLeftPoint, width, -height);
        this.upperLine = new Line(upperLeft, upperRight);
        this.lowerLine = new Line(lowerLeft, lowerRight);
        this.leftLine = new Line(lowerLeft, upperLeft);
        this.rightLine = new Line(lowerRight, upperRight);
    }

    /**
     * Determines if a point is in a list of points.
     *
     * @param points the list of points
     * @param point  the point to check
     * @return true if the point is in the list, false otherwise
     */
    public boolean isPointInList(List<Point> points, Point point) {
        for (Point p : points) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if there are any intersection points between
     * the rectangle and a specified line.
     *
     * @param line The line to check for intersection with the rectangle.
     * @return true if there is intersection point, false otherwise.
     */
    public boolean hasIntersectionPoints(Line line) {
        List<Point> points = intersectionPoints(line);
        return !points.isEmpty();
    }

    /**
     * Returns a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line to check for intersections
     * @return the List of intersection points
     */
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Point p1 = this.upperLine.intersectionWith(line);
        if (p1 != null && !isPointInList(points, p1)) {
            points.add(p1);
        }
        Point p2 = this.lowerLine.intersectionWith(line);
        if (p2 != null && !isPointInList(points, p2)) {
            points.add(p2);
        }
        Point p3 = this.leftLine.intersectionWith(line);
        if (p3 != null && !isPointInList(points, p3)) {
            points.add(p3);
        }
        Point p4 = this.rightLine.intersectionWith(line);
        if (p4 != null && !isPointInList(points, p4)) {
            points.add(p4);
        }
        return points;
    }

    /**
     * gets the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper left point.
     *
     * @return the upper left point
     */
    public Point getUpperLeftPoint() {
        return this.upperLeftPoint;
    }

    /**
     * Gets the upper line.
     *
     * @return the upper line
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * Gets the lower line.
     *
     * @return the lower line
     */
    public Line getLowerLine() {
        return this.lowerLine;
    }

    /**
     * Gets the left line.
     *
     * @return the left line
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * Gets the right line.
     *
     * @return the right line
     */
    public Line getRightLine() {
        return this.rightLine;
    }
}
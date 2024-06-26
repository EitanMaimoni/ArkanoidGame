import java.util.List;
/**
 * The line class represents a line in a 2D coordinate system.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Line {
    private static final double EPSILON = 1e-10;
    private static final double NO_SLOPE = Double.POSITIVE_INFINITY;
    private final Point start;
    private final Point end;
    private final double slope;
    private final double yIntercept;
    /**
     * Constructs a new Line.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = slopeCalculator();
        this.yIntercept = yInterceptCalculator();
    }
    /**
     * Constructs a new Line.
     *
     * @param x1 the x-coordinate of the start point of the line
     * @param y1 the y-coordinate of the start point of the line
     * @param x2 the x-coordinate of the end point of the line
     * @param y2 the y-coordinate of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope = slopeCalculator();
        this.yIntercept = yInterceptCalculator();
    }
    /**
     * Slope calculator.
     *
     * @return the slope of the line
     */
    public double slopeCalculator() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        if (isDoubleEqual(x2, x1)) {
            return NO_SLOPE;
        }
        // return the slope
        return (y2 - y1) / (x2 - x1);
    }
    /**
     * Y intercept calculator.
     *
     * @return the y-intercept of the line
     */
    public double yInterceptCalculator() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double slope = this.slope;
        return y1 - (slope * x1);
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
     * Checks whether a value is within a given range, inclusive.
     *
     * @param a the upper bound of the range
     * @param b the lower bound of the range
     * @param v the value to check
     * @return true if v is in the range [a, b], false otherwise
     */
    public boolean isInRange(double a, double b, double v) {
        if (b >= a) {
            double temp = a;
            a = b;
            b = temp;
        }
        return (a > v && v > b || isDoubleEqual(a, v) || isDoubleEqual(b, v));
    }
    /**
     * Determines whether a given value is inside a specified range.
     *
     * @param a the upper bound of the range
     * @param b the lower bound of the range
     * @param v The value to check
     * @return true if v is in the range (a, b), false otherwise
     */
    public boolean isInsideRange(double a, double b, double v) {
        if (b >= a) {
            double temp = a;
            a = b;
            b = temp;
        }
        return (a > v && v > b);
    }
    /**
     * Determines whether a given point lies on this line.
     *
     * @param point the point to be checked
     * @return true if the point lies on the line, false otherwise
     */
    public boolean isPointOnLine(Point point) {
        double lineX1 = this.start.getX();
        double lineX2 = this.end.getX();
        double lineY1 = this.start.getY();
        double lineY2 = this.end.getY();
        double pointX = point.getX();
        double pointY = point.getY();
        if (this.slope == NO_SLOPE) {
            if (isDoubleEqual(pointX, lineX1)) {
                return isInRange(lineY1, lineY2, pointY);
            }
        } else {
            // checks if point is on the line by using the
            // equation of the line (y=mx+c)
            if (Math.abs(pointY - this.slope * pointX - this.yIntercept) < EPSILON) {
                return isInRange(lineX1, lineX2, pointX);
            }
        }
        return false;
    }
    /**
     * Determines whether a given lines that we already know they have same slope and has intersection overlap.
     *
     * @param thisStart the start of the first line to be checked
     * @param thisEnd the end of the first line to be checked
     * @param otherStart the start of the second line to be checked
     * @param otherEnd the end of the second line to be checked
     * @return true if the lines overlaps, false otherwise
     */
    public boolean isLineOverlap(double thisStart, double thisEnd, double otherStart, double otherEnd) {
        return isInsideRange(otherStart, otherEnd, thisStart) || isInsideRange(otherStart, otherEnd, thisEnd)
                || isInsideRange(thisStart, thisEnd, otherStart) || isInsideRange(thisStart, thisEnd, otherEnd);
    }
    /**
     * Return the length of the line.
     *
     * @return the length of the line as a double
     */
    public double length() {
        return start.distance(end);
    }
    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line as a Point object
     */
    public Point middle() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x = (x1 + x2) / 2;
        double y = (y1 + y2) / 2;
        return new Point(x, y);
    }
    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line as a Point object
     */
    public Point start() {
        return this.start;
    }
    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line as a Point object
     */
    public Point end() {
        return this.end;
    }
    /**
     * Checks if two lines intersect.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double thisX1 = this.start.getX();
        double thisX2 = this.end.getX();
        double otherX1 = other.start.getX();
        double otherX2 = other.end.getX();
        // if one of the lines has no slope, make the check in special method
        if (this.slope == NO_SLOPE || other.slope == NO_SLOPE) {
            return this.isIntersectingVertical(other);
        }
        // if the slopes are equal, the lines are parallel or coincident
        if (isDoubleEqual(other.slope, this.slope)) {
            if (isDoubleEqual(other.yIntercept, this.yIntercept)) {
                // the lines are coincident, check if they overlap
                return (isInRange(otherX1, otherX2, thisX1) || isInRange(otherX1, otherX2, thisX2)
                        || isInRange(thisX1, thisX2, otherX1) || isInRange(thisX1, thisX2, otherX2));
            } else {
                // the lines are parallel and do not intersect
                return false;
            }
        } else {
            // the lines might intersect,
            // calculate the intersection point and checks if it in the range
            double mutualX = (this.yIntercept - other.yIntercept) / (other.slope - this.slope);
            return (isInRange(otherX1, otherX2, mutualX) && isInRange(thisX1, thisX2, mutualX));
        }
    }
    /**
     * Checks if this line intersects with another line vertically.
     *
     * @param other the other line to check intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersectingVertical(Line other) {
        double thisX1 = this.start.getX();
        double thisY1 = this.start.getY();
        double thisX2 = this.end.getX();
        double thisY2 = this.end.getY();
        double otherX1 = other.start.getX();
        double otherY1 = other.start.getY();
        double otherX2 = other.end.getX();
        double otherY2 = other.end.getY();
        if (this.slope == NO_SLOPE && other.slope == NO_SLOPE) {
            // If they have the same x-coordinate then they intersect vertically
            // if any of the y-coordinates overlap
            if (isDoubleEqual(otherX1, thisX1)) {
                return (isInRange(otherY1, otherY2, thisY1) || isInRange(otherY1, otherY2, thisY2)
                        || isInRange(thisY1, thisY2, otherY1) || isInRange(thisY1, thisY2, otherY2));
            }
            return false;
        } else if (this.slope == NO_SLOPE) {
            // Find the y-coordinate where the other line might intersect
            // with this line
            double mutualX = thisX1;
            double mutualY;
            mutualY = (other.slope) * mutualX + other.yIntercept;
            // The lines intersect vertically if the x-coordinate of the other
            // line is within the range of this line's x-coordinates
            // and the y-coordinate of the intersection point is within
            // the range of this line's y-coordinates
            return (isInRange(otherX1, otherX2, mutualX) && isInRange(thisY1, thisY2, mutualY));
        } else {
            // Otherwise, this other line has no slope
            // Find the y-coordinate where this line intersects
            // with the other line's x-coordinate
            double mutualX = otherX1;
            double mutualY;
            mutualY = (this.slope) * mutualX + this.yIntercept;
            // The lines intersect vertically if the x-coordinate of this
            // line is within the range of the other line's x-coordinates
            // and the y-coordinate of the intersection point is within
            // the range of the other line's y-coordinates
            return (isInRange(thisX1, thisX2, mutualX) && isInRange(otherY1, otherY2, mutualY));
        }
    }
    /**
     * Intersection with point.
     *
     * @param other the other line to check intersection with
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        if (this.equals(other)) {
            return null;
        }
        double thisX1 = this.start.getX();
        double thisX2 = this.end.getX();
        double otherX1 = other.start.getX();
        double otherX2 = other.end.getX();
        // if one of the lines has no slope, make the check in special method
        if (this.slope == NO_SLOPE || other.slope == NO_SLOPE) {
            return intersectionWithVertical(other);
        }
        if (isDoubleEqual(other.slope, this.slope)) {
            // Check if the two lines overlap at any point
            // (we already know we have intersection)
            if (isLineOverlap(thisX1, thisX2, otherX1, otherX2)) {
                return null;
            } else {
                // If the two lines don't overlap, and we already know that
                // there is an intersection, so it must be in the edges of the
                // line
                if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
                } else {
                    return this.end;
                }
            }
        } else {
            // If the two lines have different slopes,
            // calculate the intersection point
            double mutualX = (this.yIntercept - other.yIntercept) / (other.slope - this.slope);
            double mutualY = (other.slope) * mutualX + other.yIntercept;
            // Create a new Point object with the coordinates
            // of the intersection point
            return new Point(mutualX, mutualY);
        }
    }
    /**
     * Intersection with vertical point.
     *
     * @param other the other line to intersect with
     * @return the point of intersection, or null if there is no intersection
     */
    public Point intersectionWithVertical(Line other) {
        double thisX1 = this.start.getX();
        double thisY1 = this.start.getY();
        double thisY2 = this.end.getY();
        double otherX1 = other.start.getX();
        double otherY1 = other.start.getY();
        double otherY2 = other.end.getY();
        if (this.slope == NO_SLOPE && other.slope == NO_SLOPE) {
            // Check if the two lines overlap at any point
            // (we already know we have intersection)
            if (isLineOverlap(thisY1, thisY2, otherY1, otherY2)) {
                return null;
            } else {
                // If the two lines don't overlap, and we already know that
                // there is an intersection,
                // so it must be in the edges of the line
                if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
                } else {
                    return this.end;
                }
            }
        } else if (this.slope == NO_SLOPE) {
            double mutualY = (other.slope) * thisX1 + other.yIntercept;
            double mutualX = thisX1;
            return new Point(mutualX, mutualY);
        } else {
            double mutualY = (this.slope) * otherX1 + this.yIntercept;
            double mutualX = otherX1;
            return new Point(mutualX, mutualY);
        }
    }
    /**
     * Closest intersection to start of line point.
     *
     * @param rectangle the rectangle
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rectangle) {
        if (!rectangle.hasIntersectionPoints(this)) {
            return null;
        }
        List<Point> points = rectangle.intersectionPoints(this);
        Point closetPoint = points.get(0);
        double smallestDistance = this.start.distance(closetPoint);
        for (Point point : points) {
            if (smallestDistance > this.start.distance(point)) {
                smallestDistance = this.start.distance(point);
                closetPoint = point;
            }
        }
        return closetPoint;
    }
    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }
}
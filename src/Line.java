/**
 * The line class represents a line in a 2D coordinate system.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-04-20
 */

public class Line {
    private final Point startPoint;
    private final Point endPoint;
    private final double slope;
    private final double yIntercept;
    private static final double EPSILON = 1e-10;
    private static final double NO_SLOPE = Double.POSITIVE_INFINITY;
    // A special value representing a vertical line
    /**
     * Instantiates a new Line.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
        this.slope = slopeCalculator();
        this.yIntercept = yInterceptCalculator();
    }
    /**
     * Instantiates a new Line.
     *
     * @param x1 the x-coordinate of the start point of the line
     * @param y1 the y-coordinate of the start point of the line
     * @param x2 the x-coordinate of the end point of the line
     * @param y2 the y-coordinate of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.startPoint = new Point(x1, y1);
        this.endPoint = new Point(x2, y2);
        this.slope = slopeCalculator();
        this.yIntercept = yInterceptCalculator();
    }
    /**
     * Slope calculator.
     *
     * @return the slope of the line
     */
    public double slopeCalculator() {
        double x1, y1, x2, y2, slope;
        x1 = this.startPoint.getX();
        y1 = this.startPoint.getY();
        x2 = this.endPoint.getX();
        y2 = this.endPoint.getY();
        // If the line is vertical, return the special value NO_SLOPE
        if (Math.abs(x2 - x1) < EPSILON) {
            return NO_SLOPE;
        }
        slope = (y2 - y1) / (x2 - x1);
        return slope;
    }
    /**
     * Y intercept calculator.
     *
     * @return the y-intercept of the line
     */
    public double yInterceptCalculator() {
        double x1 = this.startPoint.getX();
        double y1 = this.startPoint.getY();
        double slope = this.slope;
        return y1 - (slope * x1);
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
        return (a > v && v > b || Math.abs(a - v) < EPSILON
                || Math.abs(b - v) < EPSILON);

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
     * Return the length of the line.
     *
     * @return the length of the line as a double
     */
    public double length() {
        return startPoint.distance(endPoint);
    }
    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line as a Point object
     */
    public Point middle() {
        double x1, y1, x2, y2;
        x1 = this.startPoint.getX();
        y1 = this.startPoint.getY();
        x2 = this.endPoint.getX();
        y2 = this.endPoint.getY();
        double x, y;
        x = (x1 + x2) / 2;
        y = (y1 + y2) / 2;
        return new Point(x, y);
    }
    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line as a Point object
     */
    public Point start() {
        return this.startPoint;
    }
    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line as a Point object
     */
    public Point end() {
        return this.endPoint;
    }
    /**
     * Checks if two lines intersect.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double thisX1 = this.startPoint.getX();
        double thisX2 = this.endPoint.getX();
        double otherX1 = other.startPoint.getX();
        double otherX2 = other.endPoint.getX();
        // if one of the lines has no slope, make the check in special method
        if (this.slope == NO_SLOPE || other.slope == NO_SLOPE) {
            return this.isIntersectingVertical(other);
        }
        // if the slopes are equal, the lines are parallel or coincident
        if (Math.abs(other.slope - this.slope) < EPSILON) {
            if (Math.abs(other.yIntercept - this.yIntercept) < EPSILON) {
                // the lines are coincident, check if they overlap
                return (isInRange(otherX1, otherX2, thisX1)
                        || isInRange(otherX1, otherX2, thisX2)
                        || isInRange(thisX1, thisX2, otherX1)
                        || isInRange(thisX1, thisX2, otherX2));
            } else {
                // the lines are parallel and do not intersect
                return false;
            }
        } else {
            // the lines might intersect,
            // calculate the intersection point and checks if it in the range
            double mutualX;
            mutualX = (this.yIntercept - other.yIntercept)
                    / (other.slope - this.slope);
            return (isInRange(otherX1, otherX2, mutualX)
                    && isInRange(thisX1, thisX2, mutualX));
        }
    }
    /**
     * Checks if this line intersects with another line vertically.
     *
     * @param other the other line to check intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersectingVertical(Line other) {
        // Get the coordinates of this line
        double thisX1 = this.startPoint.getX();
        double  thisY1 = this.startPoint.getY();
        double  thisX2 = this.endPoint.getX();
        double  thisY2 = this.endPoint.getY();
        // Get the coordinates of the other line
        double  otherX1 = other.startPoint.getX();
        double  otherY1 = other.startPoint.getY();
        double  otherX2 = other.endPoint.getX();
        double  otherY2 = other.endPoint.getY();
        // Check if both lines have no slope (i.e., are vertical)
        if (this.slope == NO_SLOPE && other.slope == NO_SLOPE) {
            // If they have the same x-coordinate within a
            // small margin of error (epsilon) then they intersect vertically
            // if any of the y-coordinates overlap
            if (Math.abs(otherX1 - thisX1) < EPSILON) {
                return (isInRange(otherY1, otherY2, thisY1)
                        || isInRange(otherY1, otherY2, thisY2)
                        || isInRange(thisY1, thisY2, otherY1)
                        || isInRange(thisY1, thisY2, otherY2));
            }
            // Otherwise, they don't intersect
            return false;
        } else if (this.slope == NO_SLOPE) {
            // Check if this line has no slope (i.e., is vertical)
            // Find the y-coordinate where the other line might intersect
            // with this line's x-coordinate
            double mutualY;
            mutualY = (other.slope) * thisX1 + other.yIntercept;
            // The lines intersect vertically if the x-coordinate of the other
            // line is within the range of this line's x-coordinates
            // and the y-coordinate of the intersection point is within
            // the range of this line's y-coordinates
            return (isInRange(otherX1, otherX2, thisX1)
                    && isInRange(thisY1, thisY2, mutualY));
        } else {
            // Otherwise, this other line has no slope
            // Find the y-coordinate where this line intersects
            // with the other line's x-coordinate
            double mutualY;
            mutualY = (this.slope) * otherX1 + this.yIntercept;
            // The lines intersect vertically if the x-coordinate of this
            // line is within the range of the other line's x-coordinates
            // and the y-coordinate of the intersection point is within
            // the range of the other line's y-coordinates
            return (isInRange(thisX1, thisX2, otherX1)
                    && isInRange(otherY1, otherY2, mutualY));
        }
    }
    /**
     * Intersection with point.
     *
     * @param other the other line to check intersection with
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        // Check if the lines intersect
        if (!isIntersecting(other)) {
            return null;
        }
        // Check if the lines are equal
        if (this.equals(other)) {
            return null;
        }
        // Get the X coordinates of the start and end points of this line
        double thisX1 = this.startPoint.getX();
        double thisX2 = this.endPoint.getX();
        // Get the X coordinates of the start and end points of the other line
        double otherX1 = other.startPoint.getX();
        double otherX2 = other.endPoint.getX();
        // if one of the lines has no slope, make the check in special method
        if (this.slope == NO_SLOPE || other.slope == NO_SLOPE) {
            return intersectionWithVertical(other);
        }
        // Check if the slopes of the two lines are equal,
        // if so, they are parallel
        if (Math.abs(other.slope - this.slope) < EPSILON) {
            // Check if the two lines overlap at any point
            if (isInsideRange(otherX1, otherX2, thisX1)
                    || isInsideRange(otherX1, otherX2, thisX2)
                    || isInsideRange(thisX1, thisX2, otherX1)
                    || isInsideRange(thisX1, thisX2, otherX2)) {
                return null;
            } else {
                // If the two lines don't overlap, and we already know that
                // there is an intersection, so it must be in the edges of the
                // line
                if (this.startPoint.equals(other.startPoint)
                        || this.startPoint.equals(other.endPoint)) {
                    return this.startPoint;
                } else {
                    return this.endPoint;
                }
            }
        } else {
            // If the two lines have different slopes,
            // calculate the intersection point
            double mutualX, mutualY;
            mutualX = (this.yIntercept - other.yIntercept)
                    / (other.slope - this.slope);
            mutualY = (other.slope) * mutualX + other.yIntercept;
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
        // Extract the x and y values of the start
        // and end points of the two lines
        double thisX1 = this.startPoint.getX();
        double thisY1 = this.startPoint.getY();
        double thisX2 = this.endPoint.getX();
        double  thisY2 = this.endPoint.getY();
        double  otherX1 = other.startPoint.getX();
        double  otherY1 = other.startPoint.getY();
        double  otherX2 = other.endPoint.getX();
        double  otherY2 = other.endPoint.getY();
        // Handle the case where both lines are vertical
        if (this.slope == NO_SLOPE && other.slope == NO_SLOPE) {
            // Check if the two lines overlap in the y direction
            if (isInsideRange(otherY1, otherY2, thisY1)
                    || isInsideRange(otherY1, otherY2, thisY2)
                    || isInsideRange(thisY1, thisY2, otherY1)
                    || isInsideRange(thisY1, thisY2, otherY2)) {
                // If there is overlap, the lines do not intersect
                return null;
            } else {
                // If the two lines don't overlap, and we already know that
                // there is an intersection,
                // so it must be in the edges of the line
                if (this.startPoint.equals(other.startPoint)
                        || this.startPoint.equals(other.endPoint)) {
                    return this.startPoint;
                } else {
                    return this.endPoint;
                }
            }
        } else if (this.slope == NO_SLOPE) {
            // Handle the case where this line is vertical
            // Check if the x-coordinate of the vertical line is within
            // the x range of the other line
            if (isInRange(otherX1, otherX2, thisX1)) {
                // Calculate the y-coordinate of the intersection point
                double mutualY;
                mutualY = (other.slope) * thisX1 + other.yIntercept;
                // Check if the y-coordinate of the intersection point
                // is within the y range of the vertical line
                if (isInRange(thisY1, thisY2, mutualY)) {
                    // If it is, return the intersection point
                    return new Point(thisX1, mutualY);
                }
            }
            // If there is no intersection, return null
            return null;
        } else {
            // Handle the case where the other line is vertical
            // Check if the x-coordinate of the other line
            // is within the x range of this line
            if (isInRange(thisX1, thisX2, otherX1)) {
                // Calculate the y-coordinate of the intersection point
                double mutualY;
                mutualY = (this.slope) * otherX1 + this.yIntercept;
                // Check if the y-coordinate of the intersection point
                // is within the y range of the other line
                if (isInRange(otherY1, otherY2, mutualY)) {
                    // If it is, return the intersection point
                    return new Point(otherX1, mutualY);
                }
            }
            // If there is no intersection, return null
            return null;
        }
    }
    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        // Check if the start and end points of this line
        // are equal to the start and end points of the other line.
        if (this.startPoint.equals(other.startPoint)
                && this.endPoint.equals(other.endPoint)) {
            return true;
        }
        // Check if the start and end points of this line
        // are equal to the end and start points of the other line.
        if (this.startPoint.equals(other.endPoint)
                && this.endPoint.equals(other.startPoint)) {
            return true;
        }
        // If none of the above conditions are met,
        // then the lines are not equal.
        return false;
    }
}



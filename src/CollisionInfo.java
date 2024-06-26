/**
 * The CollisionInfo class represents information about a collision between
 * a moving object and a collidable object.
 * It contains the point at which the collision occurs, and the collidable
 * object involved in the collision.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;
    /**
     * Constructs a new CollisionInfo object with the given
     * collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
package physics;

import geometry.Point;
import geometry.Rectangle;
import objects.Ball;

/**
 * The interface Collidable represents an object that can be collided with.
 * Every collidable object has a bounding Rectangle that represents the area in
 * which it occupies.
 * It also has a hit method that is called when a collision occurs,
 * to calculate the new velocity of the object.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-07
 */
public interface Collidable {
    /**
     * Returns the bounding rectangle of the collidable object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Calculates and returns the new velocity of the object, after a collision
     * with a given collision point and current velocity has occurred.
     *
     * @param hitter          the object that hit the collidable
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the object
     * @return the new velocity of the object after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Returns the name of the specific class the collidable belong to.
     *
     * @return the colidable name
     */
    String className();
}
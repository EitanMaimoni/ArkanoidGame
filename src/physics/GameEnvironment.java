package physics;

import java.util.ArrayList;
import java.util.List;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

/**
 * The GameEnvironment class represents the environment in which the
 * game is played.
 * It manages a collection of Collidable objects and provides methods
 * for adding new objects to the collection and checking for collisions.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment object wit an empty
     * collection of Collidable objects.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given Collidable object to this GameEnvironment's collection.
     * If the given object is null, does nothing.
     *
     * @param c the Collidable object to add
     */
    public void addCollidable(Collidable c) {
        if (c == null) {
            return;
        }
        this.collidables.add(c);
    }

    /**
     * removes the given Collidable object to this GameEnvironment's collection.
     * If the given object is null, does nothing.
     *
     * @param c the Collidable object to add
     */
    public void removeCollidable(Collidable c) {
        if (c == null) {
            return;
        }
        this.collidables.remove(c);
    }

    /**
     * Returns a List of all the Collidable.
     *
     * @return a List of all the Collidable objects
     *         in this GameEnvironment's collection
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Get Collidables On Point.
     *
     * @param collisionPoint the Point to check for intersections
     * @return a List of all the Collidable objects that intersect
     *         with the given Point
     */
    public List<Collidable> getCollidablesOnPoint(Point collisionPoint) {
        List<Collidable> collidablesList = new ArrayList<>();
        for (Collidable c : collidables) {
            if (c.getCollisionRectangle().getUpperLine().isPointOnLine(collisionPoint)
                    || c.getCollisionRectangle().getLowerLine().isPointOnLine(collisionPoint)
                    || c.getCollisionRectangle().getLeftLine().isPointOnLine(collisionPoint)
                    || c.getCollisionRectangle().getRightLine().isPointOnLine(collisionPoint)) {
                if (c.className().equals("Block")) {
                    collidablesList.add(c);
                }
            }
        }
        return collidablesList;
    }

    /**
     * Gets the closest Collision to the trajectory.
     *
     * @param trajectory the Line to check for collisions
     *                   with Collidable objects
     * @return information about the closest collision between the given Line
     *         and any Collidable objects in this GameEnvironment's collection,
     *         or null if no collisions occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisionInfoArray = new ArrayList<>();
        for (Collidable c : collidables) {
            Point p;
            Rectangle rectangle = c.getCollisionRectangle();
            p = trajectory.closestIntersectionToStartOfLine(rectangle);
            if (p != null) {
                collisionInfoArray.add(new CollisionInfo(p, c));
            }
        }
        if (collisionInfoArray.isEmpty()) {
            return null;
        } else {
            Point start = trajectory.start();
            double smallerDistance = start.distance(collisionInfoArray.get(0).collisionPoint());
            CollisionInfo closestCollision = null;
            for (CollisionInfo c : collisionInfoArray) {
                if (smallerDistance >= start.distance(c.collisionPoint())) {
                    smallerDistance = start.distance(c.collisionPoint());
                    closestCollision = c;
                }
            }
            return closestCollision;
        }
    }
}
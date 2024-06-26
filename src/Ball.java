import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Ball class represents a 2D ball object.
 * 
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Ball implements Sprite {
    private Point point;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity = null;
    private GameEnvironment environment;
    private static final int Y_AXIS_FIX = 600;
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
     * Sets the game environment for the ball.
     *
     * @param environment the environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
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
     * Add the ball to the game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * Removes the block to the specified game by adding
     * it as a sprite and collidable.
     *
     * @param gameLevel the game to add the block to
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
    @Override
    public void drawOn(DrawSurface d) {
        int x = this.getX();
        int y = this.getY();
        int r = this.getSize();
        // draw the ball as a filled circle with the given color
        // *600 - y because the original board y-axis is opposite
        d.setColor(Color.black);
        d.fillCircle(x, Y_AXIS_FIX - y, r + 1);
        d.setColor(this.getColor());
        d.fillCircle(x, Y_AXIS_FIX - y, r);
        d.setColor(Color.red);
        d.fillCircle(x, Y_AXIS_FIX - y, 2);
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Moves the ball one step according to its current velocity.
     * If the ball has no velocity, nothing happens.
     * If the ball collides with a collidable object in its trajectory,
     * its velocity is updated accordingly.
     * Otherwise, the ball moves to its new position according to its velocity.
     */
    public void moveOneStep() {
        if (velocity == null) {
            return;
        }
        double dx = velocity.getDX();
        double dy = velocity.getDY();
        double x = this.point.getX();
        double y = this.point.getY();
        Point start = new Point(x, y);
        Point end = new Point(x + dx, y + dy);
        Line trajectory = new Line(start, end);
        // get the closest collision point
        CollisionInfo closest = environment.getClosestCollision(trajectory);
        // if there is no collision, the ball moves according to its velocity
        if (closest == null) {
            this.point = this.getVelocity().applyToPoint(this.point);
            return;
        }
        Point collisionPoint = closest.collisionPoint();
        // checks if the collision point is on more than 1 collidables
        java.util.List<Collidable> collidables;
        collidables = environment.getCollidablesOnPoint(collisionPoint);
        // if there are more than 1 collidable on the collision point
        // we set flags and send the collision point and the
        // collidables to special method that will change the flags if we need
        // to change the DX or DY.
        if (collidables.size() >= 2) {
            boolean[] flag = {false, false};
            boolean[] temp = {false, false};
            for (Collidable c : collidables) {
                if (c.className().equals("Block")) {
                    Block block = (Block) c;
                    temp = block.hitMultiCollidables(this, collisionPoint, this.velocity);
                }
                if (temp[0]) {
                    flag[0] = true;
                }
                if (temp[1]) {
                    flag[1] = true;
                }
            }
            if (flag[1]) {
                dx = -dx;
            }
            if (flag[0]) {
                dy = -dy;
            }
            setVelocity(dx, dy);
        } else {
            Collidable collidable = closest.collisionObject();
            setVelocity(collidable.hit(this, collisionPoint, this.velocity));
            if (collidable.className().equals("Paddle")) {
                this.point = this.getVelocity().applyToPoint(this.point);
            }
        }
    }
}


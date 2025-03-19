package objects;

import biuoop.DrawSurface;
import collision.HitListener;
import collision.HitNotifier;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import physics.Collidable;
import physics.Velocity;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block in 2D space.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
    private final Rectangle rect;
    private final java.awt.Color color;
    private static final int Y_AXIS_FIX = 600;

    /**
     * Constructs a new Block with the specified rectangle and color.
     *
     * @param rectangle the rectangle representing the block
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rect = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Gets the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets the rectangle representing the block.
     *
     * @return the rectangle representing the block
     */
    public Rectangle getRectangle() {
        return this.rect;
    }

    /**
     * Adds the block to the specified game by adding
     * it as a sprite and collidable.
     *
     * @param g the game to add the block to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes the block to the specified game by adding
     * it as a sprite and collidable.
     *
     * @param gameLevel the game to add the block to
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Adds a hit listener to the list.
     *
     * @param hl the HitListener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the list.
     *
     * @param hl the HitListener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all hit listeners about a hit event.
     *
     * @param hitter the Ball object that caused the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Checks if the ball need to change his velocity (dx and dy), in case there
     * is more than 1 collidable on the collision point.
     *
     * @param collisionPoint  the point where the block was hit
     * @param currentVelocity the velocity of the object hitting the block
     * @param hitter          the ball that hit the block
     * @return an array of booleans (flags) indicating whether the ball should
     *         change his DX or DY.
     */
    public boolean[] hitMultiCollidables(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        boolean[] flag = { false, false };
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        if (dx > 0 && dy > 0) {
            if (this.rect.getRightLine().end().getX() > collisionPoint.getX()
                    && !(this.rect.getLeftLine().start().equals(collisionPoint))) {
                flag[1] = true;
            }
        }
        if (dx > 0 && dy < 0) {
            if (this.rect.getRightLine().end().getX() > collisionPoint.getX()
                    && !(this.rect.getLeftLine().end().equals(collisionPoint))) {
                flag[1] = true;
            }
        }
        if (dx < 0 && dy > 0) {
            if (this.rect.getLeftLine().end().getX() < collisionPoint.getX()
                    && !(this.rect.getRightLine().start().equals(collisionPoint))) {
                flag[1] = true;
            }
        }
        if (dx < 0 && dy < 0) {
            if (this.rect.getLeftLine().end().getX() < collisionPoint.getX()
                    && !(this.rect.getRightLine().end().equals(collisionPoint))) {
                flag[1] = true;
            }
        }
        if (dy > 0 && dx > 0) {
            if (this.rect.getUpperLine().end().getY() > collisionPoint.getY()
                    && !(this.rect.getLeftLine().start().equals(collisionPoint))) {
                flag[0] = true;
            }
        }
        if (dy > 0 && dx < 0) {
            if (this.rect.getUpperLine().end().getY() > collisionPoint.getY()
                    && !(this.rect.getRightLine().start().equals(collisionPoint))) {
                flag[0] = true;
            }
        }
        if (dy < 0 && dx > 0) {
            if (this.rect.getLowerLine().end().getY() < collisionPoint.getY()
                    && !(this.rect.getLeftLine().end().equals(collisionPoint))) {
                flag[0] = true;
            }
        }
        if (dy < 0 && dx < 0) {
            if (this.rect.getLowerLine().end().getY() < collisionPoint.getY()
                    && !(this.rect.getRightLine().end().equals(collisionPoint))) {
                flag[0] = true;
            }
        }
        return flag;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        if (this.rect.getLeftLine().isPointOnLine(collisionPoint) && dx > 0) {
            dx = -dx;
        }
        if (this.rect.getRightLine().isPointOnLine(collisionPoint) && dx < 0) {
            dx = -dx;
        }
        if (this.rect.getUpperLine().isPointOnLine(collisionPoint) && dy < 0) {
            dy = -dy;
        }
        if (this.rect.getLowerLine().isPointOnLine(collisionPoint) && dy > 0) {
            dy = -dy;
        }
        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface d) {
        Point point = this.rect.getUpperLeftPoint();
        int h = (int) this.rect.getHeight();
        int w = (int) this.rect.getWidth();
        int x = (int) point.getX();
        int y = (int) point.getY();
        // draw the ball as a filled circle with the given color
        // *600 - y because the original board y-axis is opposite
        d.setColor(this.color);
        d.fillRectangle(x, Y_AXIS_FIX - y, w, h);
        d.setColor(Color.black);
        d.drawRectangle(x, Y_AXIS_FIX - y, w, h);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public String className() {
        return "Block";
    }
}

/**
 * The interface HitListener represents an object that listens for hit events.
 * Classes that implement this interface should define the behavior when a hit event occurs.
 * The hitEvent method is called whenever an object is hit.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter represents the Ball object that caused the hit.
     *
     * @param beingHit the object that was hit
     * @param hitter   the Ball object that caused the hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}
package collision;

/**
 * The interface HitNotifier represents an object that can notify HitListeners
 * about hit events.
 * Classes that implement this interface should provide methods for adding and
 * removing HitListeners.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
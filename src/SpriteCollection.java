import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of sprites that can be drawn on and updated.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class SpriteCollection {
    private final List<Sprite> sprites;
    /**
     * Constructs a new sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        if (s == null) {
            return;
        }
        this.sprites.add(s);
    }
    /**
     * remove a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void removeSprite(Sprite s) {
        if (s == null) {
            return;
        }
        this.sprites.remove(s);
    }
    /**
     * Notifies all sprites that a unit of time has passed.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the hitListeners before iterating over them.
        List<Sprite> sprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite s: sprites) {
            s.timePassed();
        }
    }
    /**
     * Draws all sprites on the given draw surface.
     *
     * @param d the draw surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: sprites) {
            s.drawOn(d);
        }
    }
}
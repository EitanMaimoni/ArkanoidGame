import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of sprites that can be drawn on and updated.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-05-04
 */
public class SpriteCollection {
    private List<Sprite> sprites;
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
     * Notifies all sprites that a unit of time has passed.
     */
    public void notifyAllTimePassed() {
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
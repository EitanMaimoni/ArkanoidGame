package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class represents an animation that can be
 * stopped by a key press.
 * 
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private final String key;

    /**
     * Constructs a new KeyPressStoppableAnimation.
     *
     * @param sensor    The KeyboardSensor used for input handling.
     * @param key       The key that can stop the animation.
     * @param animation The underlying animation to be played.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.stop = false;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.key = key;
    }

    /**
     * Checks if the stop key is pressed.
     */
    public void isKeyPressed() {
        if (this.keyboard.isPressed(key) && this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
            return;
        }
        if (this.keyboard.isPressed(key)) {
            this.stop = true;
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        isKeyPressed();
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
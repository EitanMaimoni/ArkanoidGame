package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class is responsible for running animations in a GUI.
 * 
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class AnimationRunner {
    private final GUI gui;
    private final Sleeper sleeper;
    private final int framesPerSecond;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;

    /**
     * Constructs a new AnimationRunner instance.
     */
    public AnimationRunner() {
        this.gui = new GUI("Game", WINDOW_WIDTH, WINDOW_HEIGHT);
        this.sleeper = new Sleeper();
        this.framesPerSecond = 60;
    }

    /**
     * Gets the GUI associated with the animation runner.
     *
     * @return The GUI instance.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Runs the given animation.
     *
     * @param animation The animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
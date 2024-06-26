import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class handles the flow of the game, including running multiple levels and handling the final outcome.
 *
 * @author Eitan Maimoni 
 * @version 19.0.2
 * @since 2023-06-01
 */
public class GameFlow {
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final Counter score;
    /**
     * Constructs a new Game flow.
     */
    public GameFlow() {
        this.score = new Counter();
        this.runner = new AnimationRunner();
        this.keyboard = runner.getGui().getKeyboardSensor();
    }
    /**
     * Runs the given list of levels.
     *
     * @param levels the list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean playerWin = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel gameLevel = new GameLevel(levelInfo, this.runner, this.keyboard, score);
            gameLevel.initialize();
            gameLevel.run();
            if (gameLevel.getBallsCounter().getValue() <= 0) {
                Animation gameOver = new GameOver(this.score);
                Animation a2k = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, gameOver);
                this.runner.run(a2k);
                runner.getGui().close();
                playerWin = false;
                break;
            }
        }
        if (playerWin) {
            //runs only if the gui is not already closed, (the player end the game and didn't lose)
            Animation youWin = new YouWin(score);
            Animation a1k = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, youWin);
            this.runner.run(a1k);
            runner.getGui().close();
        }
    }
}

package game;

import java.util.ArrayList;
import java.util.List;

import levels.DirectHit;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;

/**
 * The Ass3Game class represents the entry point of the application,
 * and is responsible for running the game.
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-06-01
 */
public class Ass6Game {
    /**
     * The main method is the starting point of the program,
     * and creates a new Game object, initializes it, and runs it.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GameFlow gameFlow = new GameFlow();
        List<LevelInformation> levels = new ArrayList<>();
        for (String string : args) {
            if (string.equals("1")) {
                levels.add(new DirectHit());
            }
            if (string.equals("2")) {
                levels.add(new WideEasy());
            }
            if (string.equals("3")) {
                levels.add(new Green3());
            }
        }
        if (levels.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
        }
        gameFlow.runLevels(levels);
    }
}

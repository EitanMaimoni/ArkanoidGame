/**
 * The Ass3Game class represents the entry point of the application,
 * and is responsible for running the game.
 *
 *
 * @author Eitan Maimoni
 * @version 19.0.2
 * @since 2023-05-04
 */
public class Ass3Game {
    /**
     * The main method is the starting point of the program,
     * and creates a new Game object, initializes it, and runs it.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

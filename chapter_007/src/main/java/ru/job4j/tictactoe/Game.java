package ru.job4j.tictactoe;

import ru.job4j.menu.InputOutput;
import ru.job4j.tictactoe.field.Cell;
import ru.job4j.tictactoe.field.GameField;

/**
 * Game.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.02.2020
 */
public class Game {
    /** Game settings. */
    private final Settings settings;
    /** Game field. */
    private final GameField gameField;
    /** Input output. */
    private final InputOutput io;

    /**
     * Constructor.
     * @param settings game settings.
     */
    public Game(Settings settings) {
        this.settings = settings;
        this.gameField = new GameField(
                settings.getGameFieldWidth(), settings.getGameFieldHeight(), settings.getWinCombination());
        this.io = settings.getInputOutput();
    }

    /**
     * The main method of the game.
     */
    public void start() {
        int counter = settings.getGameFieldHeight() * settings.getGameFieldWidth();
        boolean exit = false;
        gameField.show(io);
        do {
            settings.getFirstPlayer().makeMove(gameField, Cell.SIGN_X);
            counter--;
            gameField.show(io);
            if (gameField.checkForVictory(Cell.SIGN_X)) {
                io.println("First player wins");
                exit = true;
            }
            if (!exit && counter != 0) {
                settings.getSecondPlayer().makeMove(gameField, Cell.SIGN_O);
                counter--;
                gameField.show(io);
                if (gameField.checkForVictory(Cell.SIGN_O)) {
                    io.println("Second player wins");
                    exit = true;
                }
            }
            if (!exit && counter == 0) {
                io.println("Draw");
                exit = true;
            }
        } while (!exit);
    }
}

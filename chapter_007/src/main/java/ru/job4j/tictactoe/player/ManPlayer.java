package ru.job4j.tictactoe.player;

import ru.job4j.menu.InputOutput;
import ru.job4j.tictactoe.field.Cell;
import ru.job4j.tictactoe.field.GameField;

/**
 * ManPlayer.
 * Human player class.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.02.2020
 */
public class ManPlayer implements Player {
    /** Input output. */
    private final InputOutput inputOutput;

    /**
     * Constructor.
     * @param inputOutput input output.
     */
    public ManPlayer(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    /**
     * Method makes a move.
     * @param gameField  game field.
     * @param cell chip for the move.
     */
    @Override
    public void makeMove(GameField gameField, Cell cell) {
        boolean exit = false;
        do {
            try {
                inputOutput.print("Input x: ");
                int x = inputOutput.getInt();
                inputOutput.print("Input y: ");
                int y = inputOutput.getInt();
                gameField.setCell(x, y, cell);
                exit = true;
            } catch (IllegalArgumentException ex) {
                inputOutput.println("Wrong coordinates. Try again.");
            }
        } while (!exit);
    }
}

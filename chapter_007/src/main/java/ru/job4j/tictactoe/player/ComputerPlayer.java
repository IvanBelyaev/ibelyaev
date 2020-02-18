package ru.job4j.tictactoe.player;

import ru.job4j.menu.InputOutput;
import ru.job4j.tictactoe.field.Cell;
import ru.job4j.tictactoe.field.GameField;

import java.util.Random;

/**
 * ComputerPlayer.
 * Computer player class.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.02.2020
 */
public class ComputerPlayer implements Player {
    /** Input output. */
    private final InputOutput inputOutput;
    /** Random. */
    private final Random random = new Random();

    /**
     * Constructor.
     * @param inputOutput input output.
     */
    public ComputerPlayer(InputOutput inputOutput) {
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
                int x = random.nextInt(gameField.getWidth());
                int y = random.nextInt(gameField.getHeight());
                gameField.setCell(x, y, cell);
                inputOutput.println("Computer make move x = " + x + ", y = " + y);
                exit = true;
            } catch (IllegalArgumentException ex) {

            }
        } while (!exit);
    }
}

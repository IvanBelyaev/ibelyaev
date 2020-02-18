package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.field.Cell;
import ru.job4j.tictactoe.field.GameField;

/**
 * Player.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.02.2020
 */
public interface Player {
    /**
     * Method makes a move.
     * @param gameField  game field.
     * @param cell chip for the move.
     */
    void makeMove(GameField gameField, Cell cell);
}

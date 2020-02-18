package ru.job4j.tictactoe.field;

import ru.job4j.menu.InputOutput;

/**
 * GameField.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.02.2020
 */
public class GameField {
    /** Width. */
    private final int width;
    /** Height. */
    private final int height;
    /** Winning Combination Length. */
    private final int winCombination;
    /** Game field. */
    private final Cell[][] field;

    /**
     * Constructor.
     * @param width - width.
     * @param height - height.
     * @param winCombination - winning combination length.
     */
    public GameField(int width, int height, int winCombination) {
        this.width = width;
        this.height = height;
        this.winCombination = winCombination;
        field = new Cell[height][width];
        init();
    }

    /**
     * The method returns playing field width.
     * @return playing field width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * The method returns playing field height.
     * @return playing field height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * The method returns winning combination length.
     * @return winning combination length.
     */
    public int getWinCombination() {
        return winCombination;
    }

    /**
     * The method returns the value of the playing field cell.
     * @param x first coordinate.
     * @param y second coordinate.
     * @return the value of the playing field cell.
     */
    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Wrong arguments.");
        }
        return field[y][x];
    }

    /**
     * The method sets the new value of the playing field cell.
     * @param x first coordinate.
     * @param y second coordinate.
     * @param cell new value of the playing field cell.
     */
    public void setCell(int x, int y, Cell cell) {
        if (x < 0 || y < 0 || x >= width || y >= height || field[y][x] != Cell.SIGN_EMPTY) {
            throw new IllegalArgumentException("Wrong arguments.");
        }
        field[y][x] = cell;
    }

    /**
     * The method is looking for a winning combination.
     * @param cell for what cell is a winning combination sought.
     * @return true if combination found otherwise false.
     */
    public boolean checkForVictory(Cell cell) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j] == cell) {
                    int counter1 = 1;
                    int counter2 = 1;
                    int counter3 = 1;
                    int counter4 = 1;
                    for (int k = 1; k < winCombination; k++) {
                        if (j + k < width && field[i][j + k] == cell) {
                            counter1++;
                        }
                        if (j + k < width && i + k < height && field[i + k][j + k] == cell) {
                            counter2++;
                        }
                        if (i + k < height && field[i + k][j] == cell) {
                            counter3++;
                        }
                        if (i + k < height && j - k >= 0 && field[i + k][j - k] == cell) {
                            counter4++;
                        }
                    }
                    if (counter1 == winCombination || counter2 == winCombination
                            || counter3 == winCombination || counter4 == winCombination) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method draws the playing field.
     * @param inputOutput input output.
     */
    public void show(InputOutput inputOutput) {
        for (int i = 0; i < height; i++) {
            inputOutput.print("|");
            for (int j = 0; j < width; j++) {
                if (field[i][j] == Cell.SIGN_O) {
                    inputOutput.print(" 0 ");
                } else if (field[i][j] == Cell.SIGN_X) {
                    inputOutput.print(" X ");
                } else {
                    inputOutput.print(" . ");
                }
            }
            inputOutput.println("|");
            inputOutput.println("");
        }
    }

    /**
     * The method fills the playing field with empty cells.
     */
    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = Cell.SIGN_EMPTY;
            }
        }
    }
}

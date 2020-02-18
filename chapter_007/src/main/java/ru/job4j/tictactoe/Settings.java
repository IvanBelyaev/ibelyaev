package ru.job4j.tictactoe;

import ru.job4j.menu.InputOutput;
import ru.job4j.tictactoe.player.ComputerPlayer;
import ru.job4j.tictactoe.player.ManPlayer;
import ru.job4j.tictactoe.player.Player;

/**
 * Settings.
 * Game settings class.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.02.2020
 */
public class Settings {
    /** Playing field width. */
    private int gameFieldWidth = 3;
    /** Playing field height. */
    private int gameFieldHeight = 3;
    /** Winning combination length. */
    private int winCombination = 3;
    /** First player. */
    private Player firstPlayer;
    /** Second player. */
    private Player secondPlayer;
    /** Input output. */
    private final InputOutput inputOutput;

    /**
     * Constructor.
     * @param inputOutput input output.
     */
    public Settings(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.firstPlayer = new ManPlayer(inputOutput);
        this.secondPlayer = new ComputerPlayer(inputOutput);
    }

    /**
     * The method returns an input input object.
     * @return an input input object.
     */
    public InputOutput getInputOutput() {
        return inputOutput;
    }

    /**
     * The method returns playing field width.
     * @return playing field width.
     */
    public int getGameFieldWidth() {
        return gameFieldWidth;
    }

    /**
     * The method sets playing field width.
     * @param gameFieldWidth a new playing field width.
     */
    public void setGameFieldWidth(int gameFieldWidth) {
        this.gameFieldWidth = gameFieldWidth;
    }

    /**
     * The method returns playing field height.
     * @return playing field height.
     */
    public int getGameFieldHeight() {
        return gameFieldHeight;
    }

    /**
     * The method sets playing field height.
     * @param gameFieldHeight a new playing field height.
     */
    public void setGameFieldHeight(int gameFieldHeight) {
        this.gameFieldHeight = gameFieldHeight;
    }

    /**
     * The method returns winning combination length.
     * @return winning combination length.
     */
    public int getWinCombination() {
        return winCombination;
    }

    /**
     * The method sets winning combination length.
     * @param winCombination a new winning combination length.
     */
    public void setWinCombination(int winCombination) {
        this.winCombination = winCombination;
    }

    /**
     * The method returns the first player.
     * @return the first player.
     */
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    /**
     * The method sets first player.
     * @param firstPlayer a new first player.
     */
    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    /**
     * The method returns the second player.
     * @return the second player.
     */
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    /**
     * The method sets second player.
     * @param secondPlayer a new second player.
     */
    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }
}

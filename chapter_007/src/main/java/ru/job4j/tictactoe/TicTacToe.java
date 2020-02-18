package ru.job4j.tictactoe;

import ru.job4j.menu.CompoundMenu;
import ru.job4j.menu.InputOutput;
import ru.job4j.menu.Menu;
import ru.job4j.tictactoe.menu.ChangeGameField;
import ru.job4j.tictactoe.menu.ChangeWinCombination;
import ru.job4j.tictactoe.menu.NewGame;
import ru.job4j.tictactoe.menu.PlayerOrder;

/**
 * TicTacToe.
 * Game start class.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.02.2020
 */
public class TicTacToe {
    /** Input output. */
    private final InputOutput io;
    /** Main menu. */
    private final Menu menu;
    /** Game settings. */
    private final Settings settings;

    /**
     * Constructor.
     * @param io input output.
     */
    public TicTacToe(InputOutput io) {
        this.io = io;
        this.settings = new Settings(io);
        menu = new CompoundMenu("Main menu", null, io);
    }

    /**
     * Starts working with the menu.
     */
    public void start() {
        fillMenu();
        menu.execute();
    }

    /**
     * Fills the menu.
     */
    private void fillMenu() {
        menu.addMenuComponent(1, new NewGame(settings));
        Menu subMenu = new CompoundMenu("Change settings", menu, io);
        subMenu.addMenuComponent(1, new PlayerOrder(settings));
        subMenu.addMenuComponent(2, new ChangeGameField(settings));
        subMenu.addMenuComponent(3, new ChangeWinCombination(settings));
        menu.addMenuComponent(2, subMenu);
    }

    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        new TicTacToe(new InputOutput(System.in, System.out)).start();
    }
}

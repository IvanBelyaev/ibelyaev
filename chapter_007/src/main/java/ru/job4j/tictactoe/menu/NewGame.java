package ru.job4j.tictactoe.menu;

import ru.job4j.menu.InputOutput;
import ru.job4j.menu.MenuItem;
import ru.job4j.tictactoe.Game;
import ru.job4j.tictactoe.Settings;

/**
 * StartGame.
 * The class is responsible for launching a new game.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.02.2020
 */
public class NewGame implements MenuItem {
    /** Input output. */
    private final InputOutput io;
    /** Game settings. */
    private final Settings settings;

    /**
     * Constructor.
     * @param settings game settings.
     */
    public NewGame(Settings settings) {
        this.io = settings.getInputOutput();
        this.settings = settings;
    }

    /**
     * The method displays a menu item.
     */
    @Override
    public void show() {
        io.println(getName());
    }

    /**
     * The method launches a new game.
     */
    @Override
    public void execute() {
        new Game(settings).start();
    }

    /**
     * The method returns the name of the menu item.
     * @return the name of the menu item.
     */
    @Override
    public String getName() {
        return "New game";
    }
}

package ru.job4j.tictactoe.menu;

import ru.job4j.menu.InputOutput;
import ru.job4j.menu.MenuComponent;
import ru.job4j.tictactoe.Settings;

/**
 * ChangeWinCombination.
 * The class is responsible for changing the length of the winning combination.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 17.02.2020
 */
public class ChangeWinCombination implements MenuComponent {
    /** Input output. */
    private final InputOutput io;
    /** Game settings. */
    private final Settings settings;

    /**
     * Constructor.
     * @param settings game settings.
     */
    public ChangeWinCombination(Settings settings) {
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
     * The method is responsible for changing the length of the winning combination.
     */
    @Override
    public void execute() {
        boolean exit;
        do {
            exit = true;
            io.print("Enter a new length of the winning combination: ");
            int winCombination = io.getInt();
            if (winCombination > 0) {
                settings.setWinCombination(winCombination);
            } else {
                exit = false;
            }
        } while (!exit);
    }

    /**
     * The method returns the name of the menu item.
     * @return the name of the menu item.
     */
    @Override
    public String getName() {
        return "Change the length of the winning combination";
    }
}

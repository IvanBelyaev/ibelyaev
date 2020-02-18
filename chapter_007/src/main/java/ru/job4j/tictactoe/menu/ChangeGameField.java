package ru.job4j.tictactoe.menu;

import ru.job4j.menu.InputOutput;
import ru.job4j.menu.MenuComponent;
import ru.job4j.tictactoe.Settings;

/**
 * ChangeGameField.
 * The class is responsible for resizing the playing field.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 17.02.2020
 */
public class ChangeGameField implements MenuComponent {
    /** Input output. */
    private final InputOutput io;
    /** Game settings. */
    private final Settings settings;

    /**
     * Constructor.
     * @param settings game settings.
     */
    public ChangeGameField(Settings settings) {
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
     * The method allows you to change the size of the playing field.
     */
    @Override
    public void execute() {
        boolean exit;
        do {
            exit = true;
            io.print("Enter the new width of the playing field: ");
            int width = io.getInt();
            io.print("Enter the new height of the playing field: ");
            int height = io.getInt();
            if (width > 0 || height > 0) {
                settings.setGameFieldWidth(width);
                settings.setGameFieldHeight(height);
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
        return "Change game field size";
    }
}

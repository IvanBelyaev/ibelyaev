package ru.job4j.tictactoe.menu;

import ru.job4j.menu.InputOutput;
import ru.job4j.menu.MenuComponent;
import ru.job4j.tictactoe.Settings;
import ru.job4j.tictactoe.player.ComputerPlayer;
import ru.job4j.tictactoe.player.ManPlayer;

/**
 * PlayerOrder.
 * The class is responsible for the choice of players.
 * You can play against a computer or a person.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 17.02.2020
 */
public class PlayerOrder implements MenuComponent {
    /** Input output. */
    private final InputOutput io;
    /** Game settings. */
    private final Settings settings;

    /**
     * Constructor.
     * @param settings game settings.
     */
    public PlayerOrder(Settings settings) {
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
     * The method sets up the players.
     */
    @Override
    public void execute() {
        setFirstPlayer();
        setSecondPlayer();
    }

    /**
     * The method sets the first player.
     */
    private void setFirstPlayer() {
        boolean exit;
        do {
            exit = true;
            io.print("Select first player (c - computer, h - human): ");
            String player = io.getString();
            if ("h".equals(player)) {
                settings.setFirstPlayer(new ManPlayer(io));
            } else if ("c".equals(player)) {
                settings.setFirstPlayer(new ComputerPlayer(io));
            } else {
                io.println("Wrong input. Try again.");
                exit = false;
            }
        } while (!exit);
    }

    /**
     * The method setss the second player.
     */
    private void setSecondPlayer() {
        boolean exit;
        do {
            exit = true;
            io.print("Select second player (c - computer, h - human): ");
            String player = io.getString();
            if ("h".equals(player)) {
                settings.setSecondPlayer(new ManPlayer(io));
            } else if ("c".equals(player)) {
                settings.setSecondPlayer(new ComputerPlayer(io));
            } else {
                io.println("Wrong input. Try again.");
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
        return "Select players";
    }
}

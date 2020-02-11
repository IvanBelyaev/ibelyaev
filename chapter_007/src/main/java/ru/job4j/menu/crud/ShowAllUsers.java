package ru.job4j.menu.crud;

import ru.job4j.menu.InputOutput;
import ru.job4j.menu.MenuItem;

import java.util.Map;

/**
 * ShowAllUser.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class ShowAllUsers implements MenuItem {
    /** Users. */
    private final Map<Integer, User> users;
    /** Input output. */
    private final InputOutput io;

    /**
     * Constructor.
     * @param users users.
     * @param io input output.
     */
    public ShowAllUsers(Map<Integer, User> users, InputOutput io) {
        this.users = users;
        this.io = io;
    }

    /**
     * The method displays a menu item.
     */
    @Override
    public void show() {
        io.println(getName());
    }

    /**
     * The method displays data for all users.
     */
    @Override
    public void execute() {
        users.values().stream().forEach(user -> { io.println(user.toString()); });
    }

    /**
     * The method returns the name of the menu item.
     * @return the name of the menu item.
     */
    @Override
    public String getName() {
        return "Show all users";
    }
}

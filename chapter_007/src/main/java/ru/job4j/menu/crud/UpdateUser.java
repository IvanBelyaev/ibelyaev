package ru.job4j.menu.crud;

import ru.job4j.menu.InputOutput;
import ru.job4j.menu.MenuItem;

import java.util.Map;

/**
 * UpdateUser.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class UpdateUser implements MenuItem {
    /** Users. */
    private final Map<Integer, User> users;
    /** Input output. */
    private final InputOutput io;

    /**
     * Constructor.
     * @param users users.
     * @param io input output.
     */
    public UpdateUser(Map<Integer, User> users, InputOutput io) {
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
     * The method updates the user data.
     */
    @Override
    public void execute() {
        io.print("Input user id: ");
        int id = io.getInt();
        if (users.get(id) == null) {
            throw new IllegalArgumentException("This id is not exist.");
        }
        io.print("Input new user name: ");
        String name = io.getString();
        io.print("Input new user age: ");
        int age = io.getInt();
        users.put(id, new User(id, name, age));
    }

    /**
     * The method returns the name of the menu item.
     * @return the name of the menu item.
     */
    @Override
    public String getName() {
        return "Update user";
    }
}

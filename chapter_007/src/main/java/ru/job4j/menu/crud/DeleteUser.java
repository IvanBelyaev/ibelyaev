package ru.job4j.menu.crud;

import ru.job4j.menu.InputOutput;
import ru.job4j.menu.MenuItem;

import java.util.Map;

/**
 * DeleteUser.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class DeleteUser implements MenuItem {
    /** Users. */
    private final Map<Integer, User> users;
    /** Input output. */
    private final InputOutput io;

    /**
     * Constructor.
     * @param users users.
     * @param io input output.
     */
    public DeleteUser(Map<Integer, User> users, InputOutput io) {
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
     * The method deletes the user.
     */
    @Override
    public void execute() {
        io.print("Input user id: ");
        int id = io.getInt();
        if (users.get(id) == null) {
            throw new IllegalArgumentException("This id is not exist.");
        }
        users.remove(id);
        io.println("User removed.");
    }

    /**
     * The method returns the name of the menu item.
     * @return the name of the menu item.
     */
    @Override
    public String getName() {
        return "Delete user";
    }
}

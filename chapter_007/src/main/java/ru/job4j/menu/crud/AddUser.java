package ru.job4j.menu.crud;

import ru.job4j.menu.InputOutput;
import ru.job4j.menu.MenuItem;

import java.util.Map;

/**
 * AddUser.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class AddUser implements MenuItem {
    /** Users. */
    private final Map<Integer, User> users;
    /** Input output. */
    private final InputOutput io;
    /** Counter. */
    private int counter = 0;

    /**
     * Constructor.
     * @param users users.
     * @param io input output.
     */
    public AddUser(Map<Integer, User> users, InputOutput io) {
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
     * The method adds the user.
     */
    @Override
    public void execute() {
        io.print("Input user name: ");
        String name = io.getString();
        io.print("Input age: ");
        int age = io.getInt();
        users.put(counter, new User(counter, name, age));
        counter++;
    }

    /**
     * The method returns the name of the menu item.
     * @return the name of the menu item.
     */
    @Override
    public String getName() {
        return "Add user";
    }
}

package ru.job4j.menu.crud;

import ru.job4j.menu.CompoundMenu;
import ru.job4j.menu.InputOutput;
import ru.job4j.menu.Menu;

import java.util.HashMap;
import java.util.Map;

/**
 * Application.
 * Demonstrates how to use the menu using the crud application as an example.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class Application {
    /** Input output. */
    private final InputOutput io;
    /** Main menu. */
    private final Menu menu;
    /** User repository. */
    private final Map<Integer, User> users = new HashMap<>();

    /**
     * Constructor.
     * @param io input output.
     */
    public Application(InputOutput io) {
        this.io = io;
         menu = new CompoundMenu("CRUD menu", null, io);
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
        menu.addMenuComponent(1, new AddUser(users, io));
        Menu subMenu = new CompoundMenu("Submenu", menu, io);
        subMenu.addMenuComponent(1, new UpdateUser(users, io));
        subMenu.addMenuComponent(2, new DeleteUser(users, io));
        menu.addMenuComponent(2, subMenu);
        menu.addMenuComponent(3, new ShowAllUsers(users, io));
    }

    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        new Application(new InputOutput(System.in, System.out)).start();
    }
}

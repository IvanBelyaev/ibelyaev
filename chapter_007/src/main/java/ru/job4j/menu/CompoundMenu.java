package ru.job4j.menu;

import java.util.Map;
import java.util.TreeMap;

/**
 * CompoundMenu.
 * The implementation of the menu interface.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class CompoundMenu implements Menu {
    /** Menu name. */
    private final String name;
    /** Menu components. */
    private final Map<Integer, MenuComponent> menuComponents = new TreeMap<>();
    /** Input output. */
    private final InputOutput io;
    /** Parent menu. */
    private final Menu parent;
    /** Prefix. Used when displaying the menu. */
    private final String prefix = "--";

    /**
     * Constructor.
     * @param name menu name.
     * @param parent parent menu.
     * @param io input output.
     */
    public CompoundMenu(String name, Menu parent, InputOutput io) {
        this.name = name;
        this.io = io;
        this.parent = parent;
    }

    /**
     * Select a menu component.
     * @param menuComponentIndex menu component number.
     */
    @Override
    public void select(int menuComponentIndex) {
        MenuComponent menuComponent = menuComponents.get(menuComponentIndex);
        if (menuComponent == null) {
            throw new IllegalArgumentException("There is no such menu item. Try again.");
        }
        menuComponent.execute();
    }

    /**
     * The method adds a new menu component to this menu.
     * @param menuComponentNumber menu component number.
     * @param menuComponent new menu component.
     */
    @Override
    public void addMenuComponent(int menuComponentNumber, MenuComponent menuComponent) {
        menuComponents.put(menuComponentNumber, menuComponent);
    }

    /**
     * The method displays a menu.
     */
    @Override
    public void show() {
        io.println(getName());
        for (Map.Entry<Integer, MenuComponent> pair : menuComponents.entrySet()) {
            io.print(getPrefix() + pair.getKey() + ". ");
            pair.getValue().show();
        }
    }

    /**
     * The method returns the prefix for components of this menu.
     * @return the prefix for the sub menu items.
     */
    @Override
    public String getPrefix() {
        String parentPrefix = "";
        if (parent != null) {
            parentPrefix =  parent.getPrefix();
        }
        return parentPrefix + prefix;
    }

    /**
     * The main menu processing cycle.
     */
    @Override
    public void execute() {
        boolean exit = false;

        do {
            io.println("");
            show();
            io.println("");
            io.print("Select menu item (type q to exit the menu): ");
            String answer = io.getString();
            io.println("");
            if (!"q".equals(answer)) {
                select(Integer.parseInt(answer));
            } else {
                exit = true;
            }
        } while (!exit);
    }

    /**
     * The method returns the name of the menu.
     * @return the name of the menu.
     */
    @Override
    public String getName() {
        return name;
    }
}
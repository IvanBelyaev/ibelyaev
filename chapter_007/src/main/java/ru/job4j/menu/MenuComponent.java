package ru.job4j.menu;

/**
 * MenuComponent.
 * Common interface for all menu items.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public interface MenuComponent {
    /**
     * Displays a menu component.
     */
    void show();

    /**
     * Performs an action when a menu component is selected.
     */
    void execute();

    /**
     * Gets the name of the menu component.
     * @return the name of the menu component.
     */
    String getName();
}

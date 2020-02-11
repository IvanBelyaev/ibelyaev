package ru.job4j.menu;

/**
 * Menu.
 * Interface for menus and submenus.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public interface Menu extends MenuComponent {
    /**
     * Select a menu component.
     * @param menuComponentIndex menu component number.
     */
    void select(int menuComponentIndex);

    /**
     * The method adds a new menu component to this menu.
     * @param menuComponentNumber menu component number.
     * @param menuComponent new menu component.
     */
    void addMenuComponent(int menuComponentNumber, MenuComponent menuComponent);

    /**
     * The method returns the prefix for components of this menu.
     * @return the prefix for the sub menu items.
     */
    String getPrefix();
}

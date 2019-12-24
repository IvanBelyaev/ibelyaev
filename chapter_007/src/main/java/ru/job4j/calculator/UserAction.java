package ru.job4j.calculator;

/**
 * UserAction.
 * Interface for the menu item.
 * @author Ivan Belyaev
 * @since 25.12.2019
 * @version 1.0
 */
public interface UserAction {
    /**
     * The method returns the number of the menu item.
     * @return the number of the menu item.
     */
    int key();

    /**
     * The method performs an action selected user.
     * @param interactCalc interactive calculator.
     * @param inputOutput the input / output system.
     */
    void execute(InteractCalc interactCalc, InputOutput inputOutput);

    /**
     * The method displays information about the action.
     * @return returns information about the action.
     */
    String info();
}

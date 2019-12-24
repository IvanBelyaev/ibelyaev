package ru.job4j.calculator;

import java.util.Map;
import java.util.TreeMap;

/**
 * ExtraMenu.
 * Additional menu for interactive calculator.
 * @author Ivan Belyaev
 * @since 25.12.2019
 * @version 2.0
 */
public class ExtraMenu {
    /** Interactive calculator. */
    private InteractCalc interactCalc;
    /** Input / output systerm. */
    private InputOutput inputOutput;
    /** Action store. */
    private Map<Integer, UserAction> extraMenu = new TreeMap<>();

    /**
     * The constructor creates a new ExtraMenu object.
     * @param interactCalc interactive calculator.
     * @param inputOutput input / output system.
     */
    public ExtraMenu(InteractCalc interactCalc, InputOutput inputOutput) {
        this.interactCalc = interactCalc;
        this.inputOutput = inputOutput;
    }

    /**
     * The method fills the menu with items.
     */
    public void feelActions() {
        extraMenu.put(1, new ReuseResultItem("Переиспользовать результат.", 1));
        extraMenu.put(2, new NewCalculationItem("Начать вычисления заново.", 2));
        extraMenu.put(3, new ExitItem("Выйти", 3));
    }

    /**
     * The method performs the selected action.
     * @param key - the number of the menu item.
     */
    public void select(int key) {
        interactCalc.allowExitFromExtraMenu();
        UserAction userAction = this.extraMenu.get(key);
        if (userAction != null) {
            userAction.execute(this.interactCalc, this.inputOutput);
        } else {
            inputOutput.printString("Ошибка. Выбранной операции не существует. Повторите попытку");
            interactCalc.denyExitFromExtraMenu();
        }
    }

    /**
     * Method shows menu.
     */
    public void show() {
        inputOutput.printString("");
        for (UserAction userAction : extraMenu.values()) {
            inputOutput.printString(userAction.info());
        }
        inputOutput.printString("");
    }

    /**
     * The menu item allows you to reuse the result of the previous calculation.
     */
    private class ReuseResultItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        ReuseResultItem(String name, int key) {
            super(name, key);
        }

        /**
         * The method returns the number of the menu item.
         * @return the number of the menu item.
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * The method performs the action of continuing the calculation.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            interactCalc.setReuseResult(true);
        }
    }

    /**
     * Menu item starts a new calculation.
     */
    private class NewCalculationItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        NewCalculationItem(String name, int key) {
            super(name, key);
        }

        /**
         * The method returns the number of the menu item.
         * @return the number of the menu item.
         */
        @Override
        public int key() {
            return 2;
        }

        /**
         * The method performs the method performs the start of a new calculation action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            interactCalc.setReuseResult(false);
        }
    }

    /**
     * Menu item exits application.
     */
    private class ExitItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        ExitItem(String name, int key) {
            super(name, key);
        }

        /**
         * The method returns the number of the menu item.
         * @return the number of the menu item.
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * The method performs the exit action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            interactCalc.setExit();
        }
    }
}
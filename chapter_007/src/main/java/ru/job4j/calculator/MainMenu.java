package ru.job4j.calculator;

import java.util.Map;
import java.util.TreeMap;

/**
 * MainMenu.
 * Main menu for interactive calculator.
 * @author Ivan Belyaev
 * @since 25.12.2019
 * @version 2.0
 */
public class MainMenu {
    /** Interactive calculator. */
    private InteractCalc interactCalc;
    /** Input / output systerm. */
    private InputOutput inputOutput;
    /** Action store. */
    private Map<Integer, UserAction> mainMenu = new TreeMap<>();

    /**
     * The constructor creates a new MainMenu object.
     * @param interactCalc interactive calculator.
     * @param inputOutput input / output system.
     */
    public MainMenu(InteractCalc interactCalc, InputOutput inputOutput) {
        this.interactCalc = interactCalc;
        this.inputOutput = inputOutput;
    }

    /**
     * The method fills the menu with items.
     */
    public void feelActions() {
        mainMenu.put(1, new AddItem("Сложить.", 1));
        mainMenu.put(2, new SubtractItem("Вычесть", 2));
        mainMenu.put(3, new MultiplyItem("Умножить", 3));
        mainMenu.put(4, new DivideItem("Разделить", 4));
        mainMenu.put(5, new SineItem("Вычислить синус", 5));
        mainMenu.put(6, new CosineItem("Вычислить косинус", 6));
        mainMenu.put(7, new TangentItem("Вычислить тангенс", 7));
        mainMenu.put(8, new ExitItem("Выйти", 8));
    }

    /**
     * The method performs the selected action.
     * @param key - the number of the menu item.
     */
    public void select(int key) {
        UserAction userAction = this.mainMenu.get(key);
        if (userAction != null) {
            userAction.execute(this.interactCalc, this.inputOutput);
        } else {
            inputOutput.printString("Ошибка. Выбранной операции не существует. Повторите попытку");
            interactCalc.doNotShowExtraMenu();
        }
    }

    /**
     * Method shows menu.
     */
    public void show() {
        inputOutput.printString("Выберите действий:");
        for (UserAction userAction : mainMenu.values()) {
            inputOutput.printString(userAction.info());
        }
        inputOutput.printString("");
    }

    /**
     * Menu item performs addition.
     */
    private class AddItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        AddItem(String name, int key) {
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
         * The method performs the add action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            startExecute(interactCalc, inputOutput);
            interactCalc.getCalculator().add(getFirstOperand(), getSecondOperand());
            finishExecute(interactCalc, inputOutput);
        }
    }

    /**
     * The menu item performs subtraction.
     */
    private class SubtractItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        SubtractItem(String name, int key) {
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
         * The method performs the subtract action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            startExecute(interactCalc, inputOutput);
            interactCalc.getCalculator().substruct(getFirstOperand(), getSecondOperand());
            finishExecute(interactCalc, inputOutput);
        }
    }

    /**
     * The menu item performs multiplication.
     */
    private class MultiplyItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        MultiplyItem(String name, int key) {
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
         * The method performs the multiply action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            startExecute(interactCalc, inputOutput);
            interactCalc.getCalculator().multiple(getFirstOperand(), getSecondOperand());
            finishExecute(interactCalc, inputOutput);
        }
    }

    /**
     * The menu item performs division.
     */
    private class DivideItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        DivideItem(String name, int key) {
            super(name, key);
        }

        /**
         * The method returns the number of the menu item.
         * @return the number of the menu item.
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * The method performs the divide action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            startExecute(interactCalc, inputOutput);
            interactCalc.getCalculator().div(getFirstOperand(), getSecondOperand());
            finishExecute(interactCalc, inputOutput);
        }
    }

    /**
     * Menu item for calculating the sine.
     */
    private class SineItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        SineItem(String name, int key) {
            super(name, key);
        }

        /**
         * The method returns the number of the menu item.
         * @return the number of the menu item.
         */
        @Override
        public int key() {
            return 5;
        }

        /**
         * The method performs the divide action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            double argument;
            if (interactCalc.isReuseResult()) {
                argument = interactCalc.getResult();
            } else {
                inputOutput.printString("Введите число: ");
                argument = inputOutput.getDouble();
            }
            interactCalc.getCalculator().sin(argument);
            finishExecute(interactCalc, inputOutput);
        }
    }

    /**
     * Menu item for calculating the cosine.
     */
    private class CosineItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        CosineItem(String name, int key) {
            super(name, key);
        }

        /**
         * The method returns the number of the menu item.
         * @return the number of the menu item.
         */
        @Override
        public int key() {
            return 6;
        }

        /**
         * The method performs the divide action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            double argument;
            if (interactCalc.isReuseResult()) {
                argument = interactCalc.getResult();
            } else {
                inputOutput.printString("Введите число: ");
                argument = inputOutput.getDouble();
            }
            interactCalc.getCalculator().cos(argument);
            finishExecute(interactCalc, inputOutput);
        }
    }

    /**
     * Menu item for calculating the tangent.
     */
    private class TangentItem extends BaseAction {
        /**
         * The overridden constructor for objects of heirs.
         *
         * @param name - the name of the menu item.
         * @param key  - the number of the menu item.
         */
        TangentItem(String name, int key) {
            super(name, key);
        }

        /**
         * The method returns the number of the menu item.
         * @return the number of the menu item.
         */
        @Override
        public int key() {
            return 7;
        }

        /**
         * The method performs the divide action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            double argument;
            if (interactCalc.isReuseResult()) {
                argument = interactCalc.getResult();
            } else {
                inputOutput.printString("Введите число: ");
                argument = inputOutput.getDouble();
            }
            interactCalc.getCalculator().tan(argument);
            finishExecute(interactCalc, inputOutput);
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
            return 8;
        }

        /**
         * The method performs the exit action.
         * @param interactCalc interactive calculator.
         * @param inputOutput the input / output system.
         */
        @Override
        public void execute(InteractCalc interactCalc, InputOutput inputOutput) {
            interactCalc.setExit();
            interactCalc.doNotShowExtraMenu();
        }
    }
}

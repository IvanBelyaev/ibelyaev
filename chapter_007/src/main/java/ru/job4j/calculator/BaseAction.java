package ru.job4j.calculator;

/**
 * BaseAction.
 * Basic implementation of UserAction for a menu item.
 * @author Ivan Belyaev
 * @since 25.12.2019
 * @version 1.0
 */
public abstract class BaseAction implements UserAction {
    /** The name of the menu item. */
    private String name;
    /** The number of the menu item. */
    private int key;
    /** First operand. */
    private double firstOperand;
    /** Second operand. */
    private double secondOperand;

    /**
     * The overridden constructor for objects of heirs.
     * @param name - the name of the menu item.
     * @param key - the number of the menu item.
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    /**
     * The method returns the first operand.
     * @return first operand.
     */
    public double getFirstOperand() {
        return firstOperand;
    }

    /**
     * The method returns the second operand.
     * @return second operand.
     */
    public double getSecondOperand() {
        return secondOperand;
    }

    /**
     * The method displays information about the action.
     * @return returns information about the action.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }

    /**
     * The method is executed before performing the main operation for most BaseActions.
     * @param interactCalc interactive calculator.
     * @param inputOutput input / output system.
     */
    protected void startExecute(InteractCalc interactCalc, InputOutput inputOutput) {
        if (interactCalc.isReuseResult()) {
            firstOperand = interactCalc.getResult();
        } else {
            inputOutput.printString("Введите первый операнд: ");
            firstOperand = inputOutput.getDouble();
        }
        inputOutput.printString("Введите второй операнд: ");
        secondOperand = inputOutput.getDouble();
    }

    /**
     * The method is executed after the main operation for most BaseActions.
     * @param interactCalc interactive calculator.
     * @param inputOutput input / output system.
     */
    protected void finishExecute(InteractCalc interactCalc, InputOutput inputOutput) {
        interactCalc.setResult(interactCalc.getCalculator().getResult());
        inputOutput.printString(String.format("Результат: %f", interactCalc.getResult()));
    }
}

package ru.job4j.calculator;

/**
 * InteractCalc.
 * Interactive calculator.
 * @author Ivan Belyaev
 * @since 24.12.2019
 * @version 2.0
 */
public class InteractCalc {
    /** Calculator. */
    private final Calculator calculator;
    /** Input / output system. */
    private final InputOutput inputOutput;
    /** Main menu. */
    private MainMenu mainMenu;
    /** Extra menu. */
    private ExtraMenu extraMenu;
    /** Whether to reuse the result in the following calculation. */
    private boolean reuseResult = false;
    /** Result. */
    private double result = 0.0;
    /** Exit main menu. */
    private boolean exitMainMenu = false;
    /** Exit extra menu. */
    private boolean exitExtraMenu = false;
    /** Show additional menu? */
    private boolean needShowExtraMenu = true;

    /**
     * Constructor creates interactive calculator.
     * @param calculator calculator, makes calculations.
     * @param inputOutput object is responsible for input output.
     */
    public InteractCalc(Calculator calculator, InputOutput inputOutput) {
        this.calculator = calculator;
        this.inputOutput = inputOutput;
        this.mainMenu = new MainMenu(this, inputOutput);
        this.extraMenu = new ExtraMenu(this, inputOutput);
        mainMenu.feelActions();
        extraMenu.feelActions();
    }

    /**
     * Entry point.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new InteractCalc(new Calculator(), new InputOutput(System.in, System.out)).mainLoop();
    }

    /**
     * The main cycle of the program.
     */
    public void mainLoop() {
        do {
            needShowExtraMenu = true;
            mainMenu.show();
            mainMenu.select(inputOutput.getInt());
            if (needShowExtraMenu) {
                do {
                    extraMenu.show();
                    extraMenu.select(inputOutput.getInt());
                } while (!exitExtraMenu);
            }
        } while (!exitMainMenu);
    }

    /**
     * The method answers whether it is necessary to use
     * the result of the previous calculation for further calculations.
     * @return true if need to reuse otherwise returns false.
     */
    public boolean isReuseResult() {
        return reuseResult;
    }

    /**
     * Sets the value of the reuseResult variable.
     * @param reuseResult new value of the reuseResult variable.
     */
    public void setReuseResult(boolean reuseResult) {
        this.reuseResult = reuseResult;
    }

    /**
     * Method returns the current result.
     * @return the current result.
     */
    public double getResult() {
        return result;
    }

    /**
     * Method sets the current result.
     * @param result calculation result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Method returns calculator.
     * @return calculator.
     */
    public Calculator getCalculator() {
        return calculator;
    }

    /**
     * Exit program.
     */
    public void setExit() {
        this.exitMainMenu = true;
    }

    /**
     * Do not show extra menu.
     */
    public void doNotShowExtraMenu() {
        this.needShowExtraMenu = false;
    }

    /**
     * The method allows exiting the additional menu.
     */
    public void allowExitFromExtraMenu() {
        this.exitExtraMenu = true;
    }

    /**
     * The method prevents the exit from the additional menu.
     */
    public void denyExitFromExtraMenu() {
        this.exitExtraMenu = false;
    }
}
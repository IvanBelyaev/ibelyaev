package ru.job4j.calculator;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * InteractCalcTest.
 * Сlass for testing an interactive calculator.
 * @author Ivan Belyaev
 * @since 24.12.2019
 * @version 2.0
 */
public class InteractCalcTest {
    /** Line separator. */
    private static final String LS = System.getProperty("line.separator");

    /**
     * The method receives the output of the program with certain input data.
     * @param inputString input data.
     * @return program output as String.
     */
    private String testCalc(String inputString) {
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new InteractCalc(new Calculator(), new InputOutput(in, out)).mainLoop();
        return new String(out.toByteArray());
    }

    /**
     * Test for addition.
     */
    @Test
    public void whenUserAddsFiveToFiveThenOutputTen() {
        String inputString = Joiner.on(LS).join("1", "5", "5", "3");
        String methodReturns = testCalc(inputString).split(LS)[9];

        assertThat(methodReturns, is(String.format("Результат: %f", 10.0)));
    }

    /**
     * Test for subtraction.
     */
    @Test
    public void whenUserSubtractsFromSevenFiveThenOutputTwo() {
        String inputString = Joiner.on(LS).join("2", "7", "5", "3");
        String methodReturns = testCalc(inputString).split(LS)[9];

        assertThat(methodReturns, is(String.format("Результат: %f", 2.0)));
    }

    /**
     * Test for multiplication.
     */
    @Test
    public void whenUserMultipliesTwoByTwoThenOutputFour() {
        String inputString = Joiner.on(LS).join("3", "2", "2", "3");
        String methodReturns = testCalc(inputString).split(LS)[9];

        assertThat(methodReturns, is(String.format("Результат: %f", 4.0)));
    }

    /**
     * Test for division.
     */
    @Test
    public void whenUserDividesFourByTwoThenOutputTwo() {
        String inputString = Joiner.on(LS).join("4", "4", "2", "3");
        String methodReturns = testCalc(inputString).split(LS)[9];

        assertThat(methodReturns, is(String.format("Результат: %f", 2.0)));
    }

    /**
     * Test for invalid operation numbers.
     */
    @Test
    public void whenUserSelectsWrongOperationsThenErrorIsDisplayed() {
        String inputString = Joiner.on(LS).join("6", "1", "2", "2", "5", "2", "5");
        String error1 = testCalc(inputString).split(LS)[7];
        String error2 = testCalc(inputString).split(LS)[23];
        String expected = "Ошибка. Выбранной операции не существует. Повторите попытку";

        assertThat(error1, is(expected));
        assertThat(error2, is(expected));
    }

    /**
     * Test for reuse result.
     */
    @Test
    public void whenUserAddsTwoAndTwoAndThreeThenOuputSeven() {
        String inputString = Joiner.on(LS).join("1", "2", "2", "1", "1", "3", "2", "5");
        String methodReturns = testCalc(inputString).split(LS)[23];

        assertThat(methodReturns, is(String.format("Результат: %f", 7.0)));
    }
}

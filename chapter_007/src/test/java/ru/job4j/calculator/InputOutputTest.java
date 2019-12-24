package ru.job4j.calculator;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * InputOutputTest.
 * class for testing input / output.
 * @author Ivan Belyaev
 * @since 24.12.2019
 * @version 2.0
 */
public class InputOutputTest {
    /**
     * Test for the getDouble method.
     */
    @Test
    public void whenGetDoubleThenMethodReturnsDoubleFromInputStream() {
        double controlNumber = 12.04;
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("%f%s", controlNumber, System.getProperty("line.separator")).getBytes()
        );
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(in, out);

        assertThat(inputOutput.getDouble(), is(controlNumber));
    }

    /**
     * Test for the getInt method.
     */
    @Test
    public void whenGetIntThenMethodReturnsIntFromInputStream() {
        int controlNumber = 5;
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("%d%s", controlNumber, System.getProperty("line.separator")).getBytes()
        );
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(in, out);

        assertThat(inputOutput.getInt(), is(controlNumber));
    }

    /**
     * Test for the printString method.
     */
    @Test
    public void whenPrintStringThenMethodWritesStringToOutputStream() {
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(in, out);
        String str = "Hello";
        inputOutput.printString(str);

        assertThat(
                new String(out.toByteArray()),
                is(String.format("%s%s", str, System.getProperty("line.separator")))
        );
    }
}

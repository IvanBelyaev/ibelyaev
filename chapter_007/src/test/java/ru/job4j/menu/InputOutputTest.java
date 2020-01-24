package ru.job4j.menu;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * InputOutputTest.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class InputOutputTest {
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
     * Test for the println method.
     */
    @Test
    public void whenPrintlnThenMethodWritesStringToOutputStream() {
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(in, out);
        String str = "Hello";
        inputOutput.println(str);

        assertThat(
                new String(out.toByteArray()),
                is(String.format("%s%s", str, System.getProperty("line.separator")))
        );
    }

    /**
     * Test for the print method.
     */
    @Test
    public void whenPrintThenMethodWritesStringToOutputStream() {
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(in, out);
        String str = "Hello";
        inputOutput.print(str);

        assertThat(
                new String(out.toByteArray()),
                is(str)
        );
    }

    /**
     * Test for the getString method.
     */
    @Test
    public void whenGetStringThenMethodReturnsStringFromInputString() {
        String str = "Hello";
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputOutput inputOutput = new InputOutput(in, out);

        assertThat(inputOutput.getString(), is(str));
    }
}

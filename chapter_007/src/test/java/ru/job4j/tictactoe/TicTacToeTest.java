package ru.job4j.tictactoe;

import com.google.common.base.Joiner;
import org.junit.Test;
import ru.job4j.menu.InputOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TicTacToeTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 18.02.2020
 */
public class TicTacToeTest {
    /** Line separator. */
    private static final String LS = System.getProperty("line.separator");

    /**
     * Test when first player wins.
     */
    @Test
    public void whenFirstPlayerWins() {
        String inputString = Joiner.on(LS).join(
                "2", "1", "h", "h", "q", "1",
                "0", "0", "0", "1", "1", "0", "1", "1", "2", "0", "q");
        String methodReturns = testApp(inputString).split(LS)[68];
        String expected = "First player wins";

        assertThat(methodReturns, is(expected));
    }

    /**
     * Test when second player wins.
     */
    @Test
    public void whenSecondPlayerWins() {
        String inputString = Joiner.on(LS).join(
                "2", "1", "h", "h", "q", "1",
                "0", "0", "0", "1", "1", "0", "1", "1", "2", "2", "2", "1", "q");
        String methodReturns = testApp(inputString).split(LS)[74];
        String expected = "Second player wins";

        assertThat(methodReturns, is(expected));
    }

    /**
     * Test when draw.
     */
    @Test
    public void whenDraw() {
        String inputString = Joiner.on(LS).join(
                "2", "1", "h", "h", "q", "1",
                "0", "0", "0", "1", "1", "0", "1", "1", "2", "2", "2", "0", "2", "1", "1", "2", "0", "2", "q");
        String methodReturns = testApp(inputString).split(LS)[92];
        String expected = "Draw";

        assertThat(methodReturns, is(expected));
    }

    /**
     * Test when change settings.
     */
    @Test
    public void whenChangeSettingsThenGameHasNewSettings() {
        String inputString = Joiner.on(LS).join(
                "2", "1", "h", "h", "2", "5", "5", "3", "4", "q", "1",
                "0", "0", "0", "1", "1", "0", "4", "4", "2", "0", "2", "2", "3", "0", "q");
        String methodReturns = testApp(inputString).split(LS)[126];
        String expected = "First player wins";

        assertThat(methodReturns, is(expected));
    }

    /**
     * The method receives the output of the program with certain input data.
     * @param inputString input data.
     * @return program output as String.
     */
    private String testApp(String inputString) {
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new TicTacToe(new InputOutput(in, out)).start();
        return new String(out.toByteArray());
    }
}

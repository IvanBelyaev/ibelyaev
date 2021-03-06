package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * IOServiceTest.
 * @author Ivan Belyaev
 * @since 25.03.2019
 * @version 1.0
 */
public class IOServiceTest {
    /**
     * Test for the isNumber method, when stream contains an even number.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenIsNumberGetsStreamWhichContainsAnEvenNumberThenReturnsTrue() throws IOException {
        final IOService ioService = new IOService();
        assertTrue(ioService.isNumber(new ByteArrayInputStream("1234567891234567891234567890".getBytes())));
    }

    /**
     * Test for the isNumber method, when stream contains an odd number.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenIsNumberGetsStreamWhichContainsAnOddNumberThenReturnsFalse() throws IOException {
        final IOService ioService = new IOService();
        assertFalse(ioService.isNumber(new ByteArrayInputStream("123456789123456789123456789".getBytes())));
    }

    /**
     * Test for the isNumber method, when stream does not contain a number.
     */
    @Test
    public void whenIsNumberGetsStreamWithOutNumberThenReturnsIOExceptionWithMessageNotANumber() {
        final IOService ioService = new IOService();
        try {
            ioService.isNumber(new ByteArrayInputStream("asd".getBytes()));
        } catch (IOException ex) {
            assertThat(ex.getMessage(), is("Not a number."));
        }
    }

    /**
     * Test for the isNumber method, when stream is empty.
     */
    @Test
    public void whenIsNumberGetsEmptyStreamThenReturnsIOExceptionWithMessageEmptyStream() {
        final IOService ioService = new IOService();
        try {
            ioService.isNumber(new ByteArrayInputStream("".getBytes()));
        } catch (IOException ex) {
            assertThat(ex.getMessage(), is("Empty stream."));
        }
    }

    /**
     * Test for the dropAbuses method.
     */
    @Test
    public void whenDropAbusesGetsInputStreamThenReturnsTheSameOutputStreamWithoutAbuses() {
        IOService ioService = new IOService();
        InputStream in = new ByteArrayInputStream("adcaa aa bb abcdaaa bb\naa\nbb\nabcaa\n".getBytes());
        OutputStream os = new ByteArrayOutputStream();

        ioService.dropAbuses(in, os, new String[] {"aa", "bb"});

        assertThat(os.toString(), is("adcaa   abcdaaa \n\n\nabcaa\n"));

    }
}
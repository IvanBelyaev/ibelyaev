package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.is;
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
        boolean expected = true;
        boolean methodReturns;
        IOService ioService = new IOService();

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream)) {

            dataOutputStream.writeInt(14);

            try (InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())) {

                methodReturns = ioService.isNumber(inputStream);
            }
        }
        assertThat(expected, is(methodReturns));
    }

    /**
     * Test for the isNumber method, when stream contains an odd number.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenIsNumberGetsStreamWhichContainsAnOddNumberThenReturnsFalse() throws IOException {
        boolean expected = false;
        boolean methodReturns;
        IOService ioService = new IOService();

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream)) {

            dataOutputStream.writeInt(15);

            try (InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())) {

                methodReturns = ioService.isNumber(inputStream);
            }
        }
        assertThat(expected, is(methodReturns));
    }
}
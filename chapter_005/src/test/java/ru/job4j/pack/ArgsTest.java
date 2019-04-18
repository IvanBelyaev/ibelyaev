package ru.job4j.pack;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ArgsTest.
 * @author Ivan Belyaev
 * @since 18.04.2019
 * @version 1.0
 */
public class ArgsTest {
    /**
     * Test with wrong number of arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongNumberOfArgumentsThenException() {
        try {
            new Args(new String[]{""});
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Wrong number of arguments."));
            throw ex;
        }
    }

    /**
     * Test with invalid arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongParametersThenException() {
        try {
            new Args(new String[]
                    {"-d", System.getProperty("java.io.tmpdir") + File.separator + "1", "-Z", "*.java", "-o", "pack.zip"});
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Wrong parameters."));
            throw ex;
        }
    }

    /**
     * Test with correct arguments.
     */
    @Test
    public void whenArgsThenNewArgs() {
        Args args = new Args(new String[]
                {"-d", System.getProperty("java.io.tmpdir") + File.separator + "1", "-e", "*.java", "-o", "pack.zip"});

        assertThat(args.directory(), is(System.getProperty("java.io.tmpdir") + File.separator + "1"));
        assertThat(args.exclude(), is("*.java"));
        assertThat(args.output(), is("pack.zip"));
    }
}

package ru.job4j.menu.crud;

import com.google.common.base.Joiner;
import org.junit.Test;

import ru.job4j.menu.InputOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ApplicationTest.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class ApplicationTest {
    /** Line separator. */
    private static final String LS = System.getProperty("line.separator");

    /**
     * The method receives the output of the program with certain input data.
     * @param inputString input data.
     * @return program output as String.
     */
    private String testApp(String inputString) {
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Application(new InputOutput(in, out)).start();
        return new String(out.toByteArray());
    }

    /**
     * Test when a user is added.
     */
    @Test
    public void whenAddUserThenUserAdded() {
        String inputString = Joiner.on(LS).join("1", "oleg", "25", "3", "q");
        String methodReturns = testApp(inputString).split(LS)[18];
        User user = new User(0, "oleg", 25);

        assertThat(methodReturns, is(user.toString()));
    }

    /**
     * Test when user data is updated.
     */
    @Test
    public void whenUpdateUserThenUserUpdated() {
        String inputString = Joiner.on(LS).join("1", "oleg", "25", "2", "1", "0", "ira", "17", "q", "3", "q");
        String methodReturns = testApp(inputString).split(LS)[39];
        User user = new User(0, "ira", 17);

        assertThat(methodReturns, is(user.toString()));
    }

    /**
     * Test when a user is deleted.
     */
    @Test
    public void whenDeleteUserThenUserDeleted() {
        String inputString = Joiner.on(LS).join("1", "oleg", "25", "2", "2", "0", "q", "3", "q");
        String methodReturns = testApp(inputString).split(LS)[24];
        User user = new User(0, "ira", 17);

        assertThat(methodReturns, is("Input user id: User removed."));
    }
}

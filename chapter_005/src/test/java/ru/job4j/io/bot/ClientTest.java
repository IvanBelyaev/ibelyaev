package ru.job4j.io.bot;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.google.common.base.Joiner;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ClientTest.
 * @author Ivan Belyaev
 * @since 02.10.2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
    /** Line separator. */
    private static final String LS = System.lineSeparator();

    /**
     * Socket.
     */
    @Mock
    private Socket socket;

    /**
     * First test.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenExitThenStop() throws IOException {
        testClient(
                Joiner.on(LS).join("exit", ""),
                Joiner.on(LS).join("exit", ""),
                Joiner.on(LS).join(
                        "Wrong command. Try again.",
                        "",
                        "Goodbye!",
                        "",
                        ""
                ));
    }

    /**
     * Second test.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenLineAndExitThenLineAndStop() throws IOException {
        testClient(
                Joiner.on(LS).join("hi", "exit", ""),
                Joiner.on(LS).join("hi", "exit", ""),
                Joiner.on(LS).join(
                        "Wrong command. Try again.",
                        "",
                        "Goodbye!",
                        "",
                        ""
                ));
    }

    /**
     * Method for testing the client.
     * @param console - user input.
     * @param out - data transfer to the server.
     * @param in - server data.
     * @throws IOException - input/output exceptions.
     */
    private void testClient(String console, String out, String in) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(in.getBytes());
        Scanner consoleInput = new Scanner(new ByteArrayInputStream(console.getBytes()), StandardCharsets.UTF_8);
        Mockito.when(socket.getOutputStream()).thenReturn(outputStream);
        Mockito.when(socket.getInputStream()).thenReturn(inputStream);
        new Client(socket, consoleInput).start();

        assertThat(outputStream.toString(), is(out));
    }
}

package ru.job4j.io.bot;

import org.mockito.Mockito;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import com.google.common.base.Joiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ServerTest.
 * @author Ivan Belyaev
 * @since 02.10.2019
 * @version 1.0
 */
public class ServerTest {
    /** Line separator. */
    private static final String LS = System.lineSeparator();

    /**
     * First test.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenNoHelloAndExitThenServerSaysWrongQuestionAndStops() throws IOException {
        testServer(
                Joiner.on(LS).join("hi", "exit", ""),
                Joiner.on(LS).join(
                        "Wrong command. Try again.",
                        "",
                        "Goodbye!",
                        "",
                        ""
                )
        );
    }

    /**
     * Second test.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenHelloAndExitThenServerSaysHelloAndStops() throws IOException {
        testServer(
                Joiner.on(LS).join("hello", "exit", ""),
                Joiner.on(LS).join(
                        "Hello, dear friend, I'm a oracle.",
                        "",
                        "Goodbye!",
                        "",
                        ""
                )
        );
    }

    /**
     * Third test.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenExitThenStopServer() throws IOException {
        testServer(
                Joiner.on(LS).join("exit", ""),
                Joiner.on(LS).join("Goodbye!", "", ""));
    }

    /**
     * Method for testing the server.
     * @param in - input.
     * @param out - output.
     * @throws IOException - input/output exceptions.
     */
    private void testServer(String in, String out) throws IOException {
        Socket socket = Mockito.mock(Socket.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(in.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Mockito.when(socket.getInputStream()).thenReturn(inputStream);
        Mockito.when(socket.getOutputStream()).thenReturn(outputStream);
        new Server(socket).start();

        assertThat(outputStream.toString(), is(out));
    }
}

package ru.job4j.io.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Server.
 * @author Ivan Belyaev
 * @since 02.10.2019
 * @version 1.0
 */
public class Server {
    /** Socket. */
    private final Socket socket;
    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(Server.class.getName());

    /**
     * The constructor creates the object Server.
     * @param socket - socket.
     */
    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * The method starts the server.
     * @throws IOException - input/output exceptions.
     */
    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if (!"exit".equals(ask)) {
                out.println("Wrong command. Try again.");
                out.println();
            }
        } while (!"exit".equals(ask));
        out.println("Goodbye!");
        out.println();
    }

    /**
     * Entry point.
     * @param args - arguments. Not used.
     * @throws IOException - input/output exceptions.
     */
    public static void main(String[] args) {
        try (Socket newSocket = new ServerSocket(8888).accept()) {
            new Server(newSocket).start();
        } catch (IOException e) {
            LOG.error("Exception in Server.main()", e);
        }
    }
}
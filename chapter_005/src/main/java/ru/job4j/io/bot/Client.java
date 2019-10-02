package ru.job4j.io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Client.
 * @author Ivan Belyaev
 * @since 02.10.2019
 * @version 1.0
 */
public class Client {
    /** Socket. */
    private final Socket socket;
    /** Standard input. */
    private final Scanner console;

    /**
     * The constructor creates the object Client.
     * @param socket - socket.
     * @param console - standard input.
     */
    public Client(Socket socket, Scanner console) {
        this.socket = socket;
        this.console = console;
    }

    /**
     * The method starts the client.
     * @throws IOException - input/output exceptions.
     */
    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        System.out.println("Connection is established");
        String question;
        do {
            System.out.println("Enter a question");
            question = console.nextLine();
            out.println(question);
            String answer = in.readLine();
            while (!answer.isEmpty()) {
                System.out.println(answer);
                answer = in.readLine();
            }
        } while (!("exit".equals(question)));
    }

    /**
     * Entry point.
     * @param args - arguments. Not used.
     * @throws IOException - input/output exceptions.
     */
    public static void main(String[] args) throws IOException {
        try (Socket newSocket = new Socket(InetAddress.getByName("localhost"), 8888)) {
            new Client(newSocket, new Scanner(System.in, StandardCharsets.UTF_8)).start();
        }
    }
}

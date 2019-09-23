package ru.job4j.chat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * ConsoleChat.
 * @author Ivan Belyaev
 * @since 08.09.2019
 * @version 1.0
 */
public class ConsoleChat {
    /** User input stream. */
    private Scanner input;

    /**
     * The constructor creates the object ConsoleChat.
     * @param in - user input stream.
     */
    public ConsoleChat(InputStream in) {
        this.input = new Scanner(in, StandardCharsets.UTF_8);
    }

    /**
     * Entry point.
     * @param args - command-line arguments.
     * @throws IOException - input/output exceptions.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Console chat started\n");
        System.out.println("exit - exit");
        System.out.println("stop - so that the robot stops responding");
        System.out.println("continue - the robot starts to respond again\n");
        new ConsoleChat(System.in).start();
    }

    /**
     * Method starts console chat.
     * @throws IOException - input/output exceptions.
     */
    public void start() throws IOException {
        List<String> answers = fillAnswers();

        Random random = new Random();
        String outputPath = System.getProperty("java.io.tmpdir") + File.separator + "log.txt";
        try (Scanner in = input;
             PrintWriter out = new PrintWriter(outputPath, StandardCharsets.UTF_8)) {

            boolean needAnswer = true;
            String userSay = in.nextLine();
            while (!"exit".equals(userSay)) {
                out.println(userSay);
                if ("stop".equals(userSay)) {
                    needAnswer = false;
                } else if ("continue".equals(userSay)) {
                    needAnswer = true;
                }
                if (needAnswer) {
                    String answer = answers.get(random.nextInt(answers.size()));
                    System.out.println(answer);
                    out.println(answer);
                }
                userSay = in.nextLine();
            }
            out.println("exit");
        }
    }

    /**
     * The method returns a list of robot responses from the resource file.
     * @return  a list of robot responses.
     * @throws IOException - input/output exceptions.
     */
    private List<String> fillAnswers() throws IOException {
        String path = ClassLoader.getSystemResource("answers.txt").getPath();
        return Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
    }
}
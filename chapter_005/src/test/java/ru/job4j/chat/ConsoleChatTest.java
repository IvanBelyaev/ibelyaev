package ru.job4j.chat;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * ConsoleChatTest.
 * @author Ivan Belyaev
 * @since 08.09.2019
 * @version 1.0
 */
public class ConsoleChatTest {
    /**
     * After the tests are completed, delete temporary files.
     * @throws IOException - input/output exceptions.
     */
    @After
    public void deleteTempFiles() throws IOException {
        String path = System.getProperty("java.io.tmpdir") + File.separator;
        Files.delete(Paths.get(path + "log.txt"));
        Files.delete(Paths.get(path + "scenario.txt"));
    }

    /**
     * The method checks the operation of console chat.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenStartThenConsoleCharWorks() throws IOException {
        String path = System.getProperty("java.io.tmpdir") + File.separator;
        try (PrintWriter out = new PrintWriter(path + "scenario.txt", StandardCharsets.UTF_8)) {
            out.println("Hello");
            out.println("Hi");
            out.println("stop");
            out.println("How do you do?");
            out.println("continue");
            out.println("exit");
        }

        new ConsoleChat(new FileInputStream(path + "scenario.txt")).start();
        List<String> answers = Files.readAllLines(
                Paths.get(ClassLoader.getSystemResource("answers.txt").getPath()), StandardCharsets.UTF_8);

        try (Scanner scanner = new Scanner(Paths.get(path + "log.txt"), StandardCharsets.UTF_8)) {
            assertThat(scanner.nextLine(), is("Hello"));
            assertTrue(answers.contains(scanner.nextLine()));
            assertThat(scanner.nextLine(), is("Hi"));
            assertTrue(answers.contains(scanner.nextLine()));
            assertThat(scanner.nextLine(), is("stop"));
            assertThat(scanner.nextLine(), is("How do you do?"));
            assertThat(scanner.nextLine(), is("continue"));
            assertTrue(answers.contains(scanner.nextLine()));
            assertThat(scanner.nextLine(), is("exit"));
            assertFalse(scanner.hasNext());
        }
    }
}

package ru.job4j.io;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * FileFinderTest.
 * @author Ivan Belyaev
 * @since 08.10.2019
 * @version 1.0
 */
public class FileFinderTest {
    /** Line separator. */
    private static final String LS = System.lineSeparator();

    /**
     * The test to run without arguments.
     * Help should be displayed.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenRunWithoutArgumentsThenShowHelp() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        FileFinder.main(new String[]{});
        assertThat(out.toString().split(LS).length, is(9));
    }

    /**
     * The test to run with the wrong number of arguments.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenRunWithWrongNumberOfArgumentsThenReturnErrorMessage() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        FileFinder.main(new String[]{"1", "2", "3"});
        assertThat(out.toString(), is("Wrong number of arguments" + LS));
    }

    /**
     * Test to run with incorrectly written arguments.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenArgumentsFailedValidationThenReturnErrorMessages() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        FileFinder.main(new String[]{"-y", "c:/", "-n", "*.txt", "-m", "-o", "log.txt"});
        assertThat(out.toString(), is("Wrong arguments. Please try again" + LS));
        out.reset();
        FileFinder.main(new String[]{"-d", "c:/", "-y", "*.txt", "-m", "-o", "log.txt"});
        assertThat(out.toString(), is("Wrong arguments. Please try again" + LS));
        out.reset();
        FileFinder.main(new String[]{"-d", "c:/", "-n", "*.txt", "-m", "-y", "log.txt"});
        assertThat(out.toString(), is("Wrong arguments. Please try again" + LS));
        out.reset();
        FileFinder.main(new String[]{"-d", System.getProperty("java.io.tmpdir"), "-n", "*.txt", "-y", "-o", "log.txt"});
        assertThat(out.toString(), is("Wrong arguments. Please use one of the following keys: -f | -m | -r" + LS));
        out.reset();
        FileFinder.main(new String[]{"-d", "cdacda:/", "-n", "*.txt", "-m", "-o", "log.txt"});
        assertThat(out.toString(), is("Invalid search directory or directory does not exist" + LS));
    }

    /**
     * Mask search test.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenSearchByMaskThenReturnCorrectResult() throws IOException {
        Path path = createSearchDirectory();
        String logFile = System.getProperty("java.io.tmpdir") + File.separator + "log.txt";
        FileFinder.main(new String[]{"-d", path.toString(), "-n", "*.?xt", "-m", "-o", logFile});
        Set<String> programReturned = new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream(logFile), StandardCharsets.UTF_16)) {
            while (scanner.hasNextLine()) {
                programReturned.add(scanner.nextLine());
            }
        }
        Set<String> expected = new HashSet<>();
        expected.add(path + File.separator + "000.txt");
        expected.add(path + File.separator + "123" + File.separator + "123.txt");
        assertTrue(programReturned.equals(expected));
        deleteTempFiles(path);
    }

    /**
     * Test for searching by file name.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenSearchByFileNameThenReturnCorrectResult() throws IOException {
        Path path = createSearchDirectory();
        String logFile = System.getProperty("java.io.tmpdir") + File.separator + "log.txt";
        FileFinder.main(new String[]{"-d", path.toString(), "-n", "456.java", "-f", "-o", logFile});
        Set<String> programReturned = new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream(logFile), StandardCharsets.UTF_16)) {
            while (scanner.hasNextLine()) {
                programReturned.add(scanner.nextLine());
            }
        }
        Set<String> expected = new HashSet<>();
        expected.add(path + File.separator + "123" + File.separator + "456.java");
        assertTrue(programReturned.equals(expected));
        deleteTempFiles(path);
    }

    /**
     * Regex Test.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenSearchByRegularExpressionThenReturnCorrectResult() throws IOException {
        Path path = createSearchDirectory();
        String logFile = System.getProperty("java.io.tmpdir") + File.separator + "log.txt";
        FileFinder.main(new String[]{"-d", path.toString(), "-n", "[0-9]*.[\\w]{4}", "-r", "-o", logFile});
        Set<String> programReturned = new HashSet<>();
        try (Scanner scanner = new Scanner(new FileInputStream(logFile), StandardCharsets.UTF_16)) {
            while (scanner.hasNextLine()) {
                programReturned.add(scanner.nextLine());
            }
        }
        Set<String> expected = new HashSet<>();
        expected.add(path + File.separator + "001.java");
        expected.add(path + File.separator + "123" + File.separator + "456.java");
        assertTrue(programReturned.equals(expected));
        deleteTempFiles(path);
    }

    /**
     * The method creates a search directory for tests.
     * @return the path to the search directory.
     * @throws IOException - input/output exceptions.
     */
    private Path createSearchDirectory() throws IOException {
        Path path = Paths.get(System.getProperty("java.io.tmpdir") + File.separator + "test");
        Files.createDirectory(path);
        Files.createFile(Paths.get(path + File.separator + "000.txt"));
        Files.createFile(Paths.get(path + File.separator + "001.java"));
        Files.createDirectory(Paths.get(path + File.separator + "123"));
        Files.createFile(Paths.get(path + File.separator + "123" + File.separator + "123.txt"));
        Files.createFile(Paths.get(path + File.separator + "123" + File.separator + "456.java"));
        return path;
    }

    /**
     * The method deletes all temporary files and directories.
     * @param path - the path to the search directory.
     * @throws IOException - input/output exceptions.
     */
    private void deleteTempFiles(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
        Files.delete(Paths.get(System.getProperty("java.io.tmpdir") + File.separator + "log.txt"));
    }
}

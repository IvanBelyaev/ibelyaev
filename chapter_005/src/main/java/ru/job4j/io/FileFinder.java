package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * FileFinder.
 * @author Ivan Belyaev
 * @since 08.10.2019
 * @version 1.0
 */
public class FileFinder {
    /** Search directory. */
    private Path searchDirectory;
    /** Output file. */
    private String outputFile;
    /** Search string. */
    private String searchString;

    /**
     * The constructor creates the object FileFinder.
     * @param args - arguments.
     * Arguments:
     * -d <path> - start search with <path>
     * -n <file> -f | -m | -r - file name (-f), mask (-m), or regular expression (-r)
     * -o <log-file> - where to save the search result
     *
     * Example:
     *      -d c:/ -n *.txt -m -o log.txt;
     *      Search for C drive text files and save the search result to a log.txt file
     */
    public FileFinder(String[] args) {
        searchDirectory = Paths.get(args[1]);
        outputFile = args[6];
        searchString = args[3];
        if ("-m".equals(args[4])) {
            searchString = searchString.replace("?", ".{1}");
            searchString = searchString.replace("*", ".*");
        }
    }

    /**
     * The method searches for files by template in the specified directory and saves the result to an output file.
     * @throws IOException - input/output exceptions.
     */
    public void find() throws IOException {
        Pattern pattern = Pattern.compile(searchString);
        try (PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream(outputFile), StandardCharsets.UTF_16))) {
            Files.walk(searchDirectory)
                    .filter(p -> !Files.isDirectory(p))
                    .filter(p -> pattern.matcher(p.getFileName().toString()).matches())
                    .forEach(writer::println);
        }
    }

    /**
     * Entry point.
     * @param args - arguments.
     * Arguments:
     * -d <path> - start search with <path>
     * -n <file> -f | -m | -r - file name (-f), mask (-m), or regular expression (-r)
     * -o <log-file> - where to save the search result
     *
     * Example:
     *      FileFinder -d c:/ -n *.txt -m -o log.txt;
     *      Search for C drive text files and save the search result to a log.txt file
     * @throws IOException - input/output exceptions.
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            showHelp();
        } else if (args.length != 7) {
            System.out.println("Wrong number of arguments");
        } else {
            if (validateArgs(args)) {
                new FileFinder(args).find();
            }
        }
    }

    /**
     * Method displays help.
     */
    private static void showHelp() {
        System.out.println("usage: FileFinder [options]");
        System.out.println("options:");
        System.out.println("\t-d <path> - start search with <path>");
        System.out.println("\t-n <file> -f | -m | -r - file name (-f), mask (-m), or regular expression (-r)");
        System.out.println("\t-o <log-file> - where to save the search result");
        System.out.println();
        System.out.println("Example:");
        System.out.println("FileFinder -d c:/ -n *.txt -m -o log.txt");
        System.out.println("\tSearch for C drive text files and save the search result to a log.txt file");
    }

    /**
     * The method checks the arguments.
     * @param args - arguments.
     * @return true if the arguments are correct otherwise returns false.
     */
    private static boolean validateArgs(String[] args) {
        boolean result = false;
        if ("-d".equals(args[0]) && "-n".equals(args[2]) && "-o".equals(args[5])) {
            Path path = Paths.get(args[1]);
            if (Files.isDirectory(path) && Files.exists(path)) {
                if ("-f".equals(args[4]) || "-m".equals(args[4]) || "-r".equals(args[4])) {
                    result = true;
                } else {
                    System.out.println("Wrong arguments. Please use one of the following keys: -f | -m | -r");
                }
            } else {
                System.out.println("Invalid search directory or directory does not exist");
            }
        } else {
            System.out.println("Wrong arguments. Please try again");
        }
        return result;
    }
}
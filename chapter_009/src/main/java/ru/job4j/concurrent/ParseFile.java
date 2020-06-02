package ru.job4j.concurrent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * ParseFile.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 02.06.2020
 */
public class ParseFile {
    /** File. */
    private File file;

    /**
     * Sets file.
     * @param f new file.
     */
    public synchronized void setFile(File f) {
        file = f;
    }

    /**
     * Gets file.
     * @return current file.
     */
    public synchronized File getFile() {
        return file;
    }

    /**
     * Reading a file into a String.
     * @return file content as a string.
     * @throws IOException input / output exceptions.
     */
    public synchronized String getContent() throws IOException {
        return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
    }

    /**
     * Reads only ASCII characters into a string.
     * @return a string containing only ASCII characters from a file.
     * @throws IOException input / output exception.
     */
    public synchronized String getContentWithoutUnicode() throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = reader.read()) > 0) {
                if (data < 0x80) {
                    result.append((char) data);
                }
            }
        }
        return result.toString();
    }

    /**
     * Writes a text string to a file.
     * @param content new content.
     * @throws IOException input / output exceptions.
     */
    public synchronized void saveContent(String content) throws IOException {
        Files.writeString(file.toPath(), content);
    }
}

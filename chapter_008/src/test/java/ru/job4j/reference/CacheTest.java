package ru.job4j.reference;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CacheTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 18.04.2020
 */
public class CacheTest {
    /** Line separator. */
    private static final String LS = System.lineSeparator();

    /**
     * A test for the case when the file is requested for the first time
     * or when the data from the file has disappeared from the cache.
     * @throws IOException input / output exceptions.
     */
    @Test
    public void whenFileIsAskedForTheFirstTimeThenReadFromFile() throws IOException {
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected, "Hello, World!", "Data from File");
        File tempFile = File.createTempFile("tempFile", ".txt");
        Files.write(Paths.get(tempFile.getAbsolutePath()), expected, StandardCharsets.UTF_8);

        Cache cache = new Cache();
        List<String> dataFromFile = cache.getDataFrom(tempFile.getAbsolutePath());

        assertThat(dataFromFile, is(expected));

        tempFile.delete();
    }

    /**
     * A test for the case when the data from the file is already in the cache.
     * @throws IOException input / output exceptions.
     */
    @Test
    public void whenFileIsAskedForTheSecondTimeThenReadFromCache() throws IOException {
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected, "Hello, World!", "Data from File");
        File tempFile = File.createTempFile("tempFile", ".txt");
        Files.write(Paths.get(tempFile.getAbsolutePath()), expected, StandardCharsets.UTF_8);

        Cache cache = new Cache();
        List<String> dataFromFile = cache.getDataFrom(tempFile.getAbsolutePath());

        assertThat(dataFromFile, is(expected));

        Files.write(Paths.get(tempFile.getAbsolutePath()), Arrays.asList(), StandardCharsets.UTF_8);

        assertThat(Files.readAllLines(Path.of(tempFile.getAbsolutePath())), is(new ArrayList<String>()));
        dataFromFile = cache.getDataFrom(tempFile.getAbsolutePath());
        assertThat(dataFromFile, is(expected));

        tempFile.delete();
    }
}


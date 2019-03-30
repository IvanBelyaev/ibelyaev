package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SearchTest.
 * @author Ivan Belyaev
 * @since 30.03.2019
 * @version 1.0
 */
public class SearchTest {
    /**
     * First test for the files method.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenFilesGetsDirectoryThenReturnsAllFilesInDirectoryWithNeedsExtensionsTXT() throws IOException {
        createTree();

        Search search = new Search();
        List<File> files = search.files(
                System.getProperty("java.io.tmpdir") + File.separator + "1", Arrays.asList("txt"));

        Set<String> set = new HashSet<>();
        for (File file : files) {
            set.add(file.getName());
        }

        assertThat(set.size(), is(2));
        assertTrue(set.contains("111.txt"));
        assertTrue(set.contains("333.txt"));

        deleteTree(new File(System.getProperty("java.io.tmpdir")  + File.separator + "1"));
    }

    /**
     * Second test for the files method.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenFilesGetsDirectoryThenReturnsAllFilesInDirectoryWithNeedsExtensionsJava() throws IOException {
        createTree();

        Search search = new Search();
        List<File> files = search.files(
                System.getProperty("java.io.tmpdir") + File.separator + "1", Arrays.asList("java"));

        Set<String> set = new HashSet<>();
        for (File file : files) {
            set.add(file.getName());
        }

        assertThat(set.size(), is(1));
        assertTrue(set.contains("222.java"));

        deleteTree(new File(System.getProperty("java.io.tmpdir")  + File.separator + "1"));
    }

    /**
     * Third test for the files method.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenFilesGetsDirectoryThenReturnsAllFilesInDirectoryWithNeedsExtensionsTXTAndJava() throws IOException {
        createTree();

        Search search = new Search();
        List<File> files = search.files(
                System.getProperty("java.io.tmpdir") + File.separator + "1", Arrays.asList("txt", "java"));

        Set<String> set = new HashSet<>();
        for (File file : files) {
            set.add(file.getName());
        }

        assertThat(set.size(), is(3));
        assertTrue(set.contains("222.java"));
        assertTrue(set.contains("111.txt"));
        assertTrue(set.contains("333.txt"));

        deleteTree(new File(System.getProperty("java.io.tmpdir")  + File.separator + "1"));
    }

    /**
     * Fourth test for the files method.
     * @throws IOException - input/output exceptions.
     */
    @Test
    public void whenFilesGetsDirectoryThenReturnsAllFilesInDirectoryWithNeedsExtensionsRAR() throws IOException {
        createTree();

        Search search = new Search();
        List<File> files = search.files(
                System.getProperty("java.io.tmpdir") + File.separator + "1", Arrays.asList("rar"));

        Set<String> set = new HashSet<>();
        for (File file : files) {
            set.add(file.getName());
        }

        assertThat(set.size(), is(0));

        deleteTree(new File(System.getProperty("java.io.tmpdir")  + File.separator + "1"));
    }

    /**
     * Create the tree.
     * @throws IOException - input/output exceptions.
     */
    public void createTree() throws IOException {
        /*
            Create the tree.

            root
              |----1
                   |----111.txt
                   |
                   |----2
                   |    |----4
                   |    |
                   |    |
                   |    |----222.java
                   |
                   |----3
                   |    |----333.txt

         */
        String root = System.getProperty("java.io.tmpdir");
        new File(root + File.separator + "1").mkdir();
        new File(root + File.separator + "1" + File.separator + "2").mkdir();
        new File(root + File.separator + "1" + File.separator + "3").mkdir();
        new File(root + File.separator + "1" + File.separator + "2" + File.separator + "4").mkdir();
        new File(root + File.separator + "1" + File.separator + "111.txt").createNewFile();
        new File(root + File.separator + "1" + File.separator
                + "2" + File.separator + "222.java").createNewFile();
        new File(root + File.separator + "1" + File.separator
                + "3" + File.separator + "333.txt").createNewFile();
    }

    /**
     * Delete a directory.
     * @param root - a directory.
     */
    public void deleteTree(File root) {
        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                deleteTree(file);
            } else {
                file.delete();
            }
        }
        root.delete();
    }
}

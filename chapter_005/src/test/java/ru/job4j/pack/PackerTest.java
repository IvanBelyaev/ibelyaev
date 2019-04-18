package ru.job4j.pack;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * PackerTest.
 * @author Ivan Belyaev
 * @since 18.04.2019
 * @version 1.0
 */
public class PackerTest {
    /** Expected file names. */
    private Set<String> expectedFileNames = new HashSet<>();

    /**
     * First test for class Packer.
     * @throws IOException - input / output exceptions.
     */
    @Test
    public void whenPackerWithoutJavaThenPacksWithoutJava() throws IOException {
        createTree();

        Packer.main(new String[]
                {"-d", System.getProperty("java.io.tmpdir") + File.separator + "1", "-e", "*.java", "-o", "pack.zip"});

        expectedFileNames.add(File.separator + "1" + File.separator + "2" + File.separator);
        expectedFileNames.add(File.separator + "1" + File.separator + "3" + File.separator);
        expectedFileNames.add(File.separator + "1" + File.separator + "2" + File.separator + "4" + File.separator);
        expectedFileNames.add(File.separator + "1" + File.separator + "111.txt");
        expectedFileNames.add(File.separator + "1" + File.separator + "3" + File.separator + "333.txt");

        ZipInputStream zin = new ZipInputStream(
                new FileInputStream(
                        System.getProperty("java.io.tmpdir") + File.separator + "1" + File.separator + "pack.zip"));
        int count = 0;
        ZipEntry zipEntry;
        while ((zipEntry = zin.getNextEntry()) != null) {
            assertTrue(expectedFileNames.contains(zipEntry.getName()));
            count++;
        }
        assertThat(count, is(expectedFileNames.size()));

        deleteTree(new File(System.getProperty("java.io.tmpdir") + File.separator + "1"));
    }

    /**
     * Second test for class Packer.
     * @throws IOException - input / output exceptions.
     */
    @Test
    public void whenPackerWithoutTxtThenPacksWithoutTxt() throws IOException {
        createTree();

        Packer.main(new String[]
                {"-d", System.getProperty("java.io.tmpdir") + File.separator + "1", "-e", "*.txt", "-o", "pack.zip"});

        expectedFileNames.add(File.separator + "1" + File.separator + "2" + File.separator);
        expectedFileNames.add(File.separator + "1" + File.separator + "3" + File.separator);
        expectedFileNames.add(File.separator + "1" + File.separator + "2" + File.separator + "4" + File.separator);
        expectedFileNames.add(File.separator + "1" + File.separator + "2" + File.separator + "222.java");


        ZipInputStream zin = new ZipInputStream(
                new FileInputStream(
                        System.getProperty("java.io.tmpdir") + File.separator + "1" + File.separator + "pack.zip"));
        int count = 0;
        ZipEntry zipEntry;
        while ((zipEntry = zin.getNextEntry()) != null) {
            assertTrue(expectedFileNames.contains(zipEntry.getName()));
            count++;
        }
        assertThat(count, is(expectedFileNames.size()));

        deleteTree(new File(System.getProperty("java.io.tmpdir") + File.separator + "1"));
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

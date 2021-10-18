package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * AnalizyTest.
 * @author Ivan Belyaev
 * @since 08.04.2019
 * @version 1.0
 */
public class AnalizyTest {
    /**
     * Test for the unavailable method.
     * @throws IOException - input / output exceptions.
     */
    @Test
    public void whenUnavailableThenCreateFileWithResult() throws IOException {
        createServerEventFile();
        createExpectedFile();

        Analizy analizy = new Analizy();
        analizy.unavailable(
                System.getProperty("java.io.tmpdir")  + File.separator + "serverLog.txt",
                System.getProperty("java.io.tmpdir")  + File.separator + "result.txt");

        try (
                BufferedReader result = new BufferedReader(
                        new FileReader(System.getProperty("java.io.tmpdir")  + File.separator + "result.txt"));
                BufferedReader expect = new BufferedReader(
                        new FileReader(System.getProperty("java.io.tmpdir")  + File.separator + "expect.txt"))) {
            String resultLine = "";
            String expectLine = "";
            while ((resultLine = result.readLine()) != null) {
                expectLine = expect.readLine();
                assertThat(resultLine, is(expectLine));
            }
            assertNull(result.readLine());
            assertNull(expect.readLine());
        }

        deleteFiles();
    }

    /**
     * This method creates the server log file.
     * @throws IOException - input / output exceptions.
     */
    public void createServerEventFile() throws IOException {
        String root = System.getProperty("java.io.tmpdir");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(root + File.separator + "serverLog.txt"))) {
            bw.write("200 10:56:01\n");
            bw.write("200 10:57:01\n");
            bw.write("400 10:58:01\n");
            bw.write("200 10:59:01\n");
            bw.write("500 11:01:02\n");
            bw.write("200 11:02:02\n");
            bw.write("400 11:05:02\n");
            bw.write("400 11:06:02\n");
            bw.write("500 11:07:02\n");
            bw.write("500 11:08:02\n");
            bw.write("400 11:09:02\n");
            bw.write("300 11:10:02\n");
            bw.write("500 11:11:02\n");
            bw.write("200 11:12:02\n");
        }
    }

    /**
     * This method generates a file that contains the expected result.
     * @throws IOException - input / output exceptions.
     */
    public void createExpectedFile() throws IOException {
        String root = System.getProperty("java.io.tmpdir");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(root + File.separator + "expect.txt"))) {
            bw.write("10:58:01;10:59:01\n");
            bw.write("11:01:02;11:02:02\n");
            bw.write("11:05:02;11:10:02\n");
            bw.write("11:11:02;11:12:02\n");
        }
    }

    /**
     * This method deletes temporary files.
     */
    public void deleteFiles() {
        String root = System.getProperty("java.io.tmpdir");
        new File(root + File.separator + "serverLog.txt").delete();
        new File(root + File.separator + "expect.txt").delete();
        new File(root + File.separator + "result.txt").delete();
    }
}

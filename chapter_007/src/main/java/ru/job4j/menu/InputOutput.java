package ru.job4j.menu;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * InputOutput.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class InputOutput {
    /** Input. */
    private final Scanner in;
    /** Output. */
    private final PrintWriter out;

    /**
     * Constructor creates an InputOutput object.
     * @param in input.
     * @param out output.
     */
    public InputOutput(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out);
    }

    /**
     * Method gets int from input.
     * @return int from input.
     */
    public int getInt() {
        return Integer.parseInt(getString());
    }

    /**
     * Method outputs a string to the output stream.
     * A line is displayed with a line break.
     * @param str output string.
     */
    public void println(String str) {
        this.print(str + System.lineSeparator());
    }

    /**
     * Method outputs a string to the output stream.
     * A string is output without a line break.
     * @param str output string.
     */
    public void print(String str) {
        out.print(str);
        out.flush();
    }

    /**
     * Method gets string from input.
     * @return string from input.
     */
    public String getString() {
        return in.nextLine();
    }
}

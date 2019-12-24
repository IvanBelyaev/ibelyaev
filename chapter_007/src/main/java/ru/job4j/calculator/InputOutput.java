package ru.job4j.calculator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * InputOutput.
 * Class for input output.
 * @author Ivan Belyaev
 * @since 24.12.2019
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
     * Method gets double from input.
     * @return double from input.
     */
    public double getDouble() {
        return in.nextDouble();
    }

    /**
     * Method gets int from input.
     * @return int from input.
     */
    public int getInt() {
        return in.nextInt();
    }

    /**
     * Method outputs a string to the output stream.
     * @param str output string.
     */
    public void printString(String str) {
        out.println(str);
        out.flush();
    }
}
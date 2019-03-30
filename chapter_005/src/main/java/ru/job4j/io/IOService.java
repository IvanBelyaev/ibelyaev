package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * IOService.
 * @author Ivan Belyaev
 * @since 25.03.2019
 * @version 1.0
 */
public class IOService {
    /**
     * The method checks that an even number is written in the byte stream.
     * @param in - input stream.
     * @return true if in the stream an even number is otherwise falseю
     * @throws IOException - input/output exceptions.
     */
    boolean isNumber(InputStream in) throws IOException {
        int lastDigit = -1;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            int symbol;
            while ((symbol = br.read()) != -1) {
                if (Character.isDigit(symbol)) {
                    lastDigit = Character.digit(symbol, 10);
                } else {
                    throw new IOException("Not a number.");
                }
            }
        }

        if (lastDigit == -1) {
            throw new IOException("Empty stream.");
        }

        return lastDigit % 2 == 0;
    }

    /**
     * The method removes all forbidden words from the input stream,
     * the received stream transfers to the output stream.
     * @param in - input stream.
     * @param out - output stream.
     * @param abuse - words removed from the streamю
     */
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, Charset.forName("UTF-8")))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : abuse) {
                    String regex = "\\b" + word + "\\b";
                    line = line.replaceAll(regex, "");
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

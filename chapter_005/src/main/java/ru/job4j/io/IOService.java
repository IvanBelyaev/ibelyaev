package ru.job4j.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        boolean result = false;
        try (DataInputStream dataInputStream = new DataInputStream(in)) {
            int n = dataInputStream.readInt();
            if (n % 2 == 0) {
                result = true;
            }
        }

        return result;
    }
}

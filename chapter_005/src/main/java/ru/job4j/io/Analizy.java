package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Analizy.
 * @author Ivan Belyaev
 * @since 08.04.2019
 * @version 1.0
 */
public class Analizy {
    /**
     * The method writes periods to the result file when the server was unavailable (codes 400 and 500).
     * @param source - a server log file.
     * @param target - result file.
     */
    public void unavailable(String source, String target) {
        try (
                Scanner scanner = new Scanner(new FileReader(source));
                BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            boolean serverWorks = true;
            String serverDown = "-";
            String serverUp = "-";
            while (scanner.hasNext()) {
                int code = scanner.nextInt();
                String time = scanner.nextLine();
                if (serverWorks && (code == 400 || code == 500)) {
                    serverWorks = false;
                    serverDown = time.trim();
                } else if (!serverWorks && (code != 400 && code != 500)) {
                    serverWorks = true;
                    serverUp = time.trim();
                    bw.write(serverDown + ";" + serverUp);
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

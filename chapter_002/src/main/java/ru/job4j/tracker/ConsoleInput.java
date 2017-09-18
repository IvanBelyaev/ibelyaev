package ru.job4j.tracker;

import java.util.Scanner;

/**
 * ConsoleInput.
 * @author Ivan Belyaev
 * @since 18.09.2017
 * @version 1.0
 */
public class ConsoleInput implements Input {
    /** Object for reading from the console data entered by the user. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * The method asks the user via the console.
     * @param question - a question to the user.
     * @return returns the user response.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}

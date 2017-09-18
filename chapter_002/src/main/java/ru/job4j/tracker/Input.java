package ru.job4j.tracker;

/**
 * Input.
 * @author Ivan Belyaev
 * @since 18.09.2017
 * @version 1.0
 */
public interface Input {
    /**
     * The method asks the user.
     * @param question - a question to the user.
     * @return returns the user response.
     */
    String ask(String question);
}
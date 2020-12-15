package ru.job4j.cache;

/**
 * OptimisticException.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 07.12.2020
 */
public class OptimisticException extends RuntimeException {
    /**
     * Constructor.
     * @param message the detail message.
     */
    public OptimisticException(String message) {
        super(message);
    }
}

package ru.job4j.concurrent;

/**
 * ConcurrentOutput.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 12.05.2020
 */
public class ConcurrentOutput {
    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}

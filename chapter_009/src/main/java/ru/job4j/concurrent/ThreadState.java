package ru.job4j.concurrent;

/**
 * ThreadState.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 12.05.2020
 */
public class ThreadState {
    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        Thread first = new Thread(
            () -> { }
        );
        Thread second = new Thread(
            () -> { }
        );
        printThreadsState(first, second);
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            printThreadsState(first, second);
        }
        printThreadsState(first, second);
    }

    /**
     * Prints thread's name and its state.
     * @param threads threads.
     */
    private static void printThreadsState(Thread... threads) {
        for (Thread thread : threads) {
            System.out.printf("%s - %s%n", thread.getName(), thread.getState());
        }
    }
}

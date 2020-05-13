package ru.job4j.concurrent;

/**
 * ConsoleProgress.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 12.05.2020
 */
public class ConsoleProgress implements Runnable {
    /** Symbols for progress bar. */
    private final String[] symbols = {"—", "\\", "|", "/"};
    /** Index for symbols array. */
    private int index = 0;

    /**
     * Simulates a download.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.printf("\r Loading ... %s", getProgressBarSymbol());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.printf("%n%s is interrupted%n", Thread.currentThread().getName());
                return;
            }
        }
    }

    /**
     * Returns the next symbol for the progress bar.
     * @return the next symbol for the progress bar.
     */
    private String getProgressBarSymbol() {
        if (index == symbols.length) {
            index = 0;
        }
        return symbols[index++];
    }

    /**
     * Entry point.
     * @param args command line arguments. Not used.
     * @throws InterruptedException - exception.
     */
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}

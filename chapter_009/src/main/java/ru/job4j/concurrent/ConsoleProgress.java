package ru.job4j.concurrent;

/**
 * ConsoleProgress.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 12.05.2020
 */
public class ConsoleProgress implements Runnable {
    /**
     * Simulates a download.
     */
    @Override
    public void run() {
        final String[] symbols = {"—", "\\", "|", "/"};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (index == symbols.length) {
                index = 0;
            }
            System.out.printf("\r Loading ... %s", symbols[index++]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.printf("%n%s is interrupted%n", Thread.currentThread().getName());
                return;
            }
        }
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

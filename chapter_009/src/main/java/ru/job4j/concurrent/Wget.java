package ru.job4j.concurrent;

/**
 * Wget.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 12.05.2020
 */
public class Wget {
    /**
     * Entry point.
     * @param args command line arguments. Not used.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    for (int index = 0; index <= 100; index++) {
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        System.out.print("\rLoading : " + index  + "%");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.printf("%nLoading interrupted%n");
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
        progress.join();
    }
}

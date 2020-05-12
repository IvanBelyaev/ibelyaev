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
     */
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    for (int index = 0; index <= 100; index++) {
                        System.out.print("\rLoading : " + index  + "%");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.start();
    }
}

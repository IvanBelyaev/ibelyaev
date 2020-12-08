package ru.job4j.concurrent;

import ru.job4j.structures.SimpleBlockingQueue;

/**
 * ParallelSearch.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 05.12.2020
 */
public class ParallelSearch {
    /**
     * Entry point.
     * @param args - command line arguments. Not used.
     */
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(3);
        final Thread consumer = new Thread(
            () -> {
                while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println(queue.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        );
        consumer.start();
        new Thread(
            () -> {
                for (int index = 0; index != 3; index++) {
                    queue.offer(index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                consumer.interrupt();
            }

        ).start();
    }
}

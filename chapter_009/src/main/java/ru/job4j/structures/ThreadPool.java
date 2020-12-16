package ru.job4j.structures;

import java.util.LinkedList;
import java.util.List;

/**
 * ThreadPool.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 16.12.2020
 */
public class ThreadPool {
    /** Pool threads. */
    private final List<Thread> threads = new LinkedList<>();
    /** Task queue. */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);
    /** Task handler. */
    private final Runnable process = () -> {
        while (!Thread.interrupted()) {
            try {
                tasks.poll().run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    };

    /**
     * Constructor.
     */
    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(process));
        }
        threads.forEach(Thread::start);
    }

    /**
     * Adds tasks to the queue.
     * @param job new task.
     */
    public void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * Ends the thread pool.
     */
    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}

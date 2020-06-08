package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * CountBarrier.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 08.06.2020
 */
@ThreadSafe
public class CountBarrier {
    /** Monitor. */
    private final Object monitor = this;

    /** Maximum for counter. */
    private final int total;

    /** Counter. */
    @GuardedBy("monitor")
    private int count = 0;

    /**
     * Constructor.
     * @param total maximum for counter.
     */
    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * Increases counter.
     * If the counter is equal to the maximum then notify all threads
     * that are waiting in the await () method.
     */
    public void count() {
        synchronized (monitor) {
            if (count != total) {
                count++;
            } else {
                System.out.println("count == total");
                monitor.notifyAll();
            }
        }
    }

    /**
     * The method waits until the counter reaches its maximum.
     */
    public void await() {
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (!Thread.currentThread().isInterrupted()) {
                doWork();
            }
        }
    }

    /**
     * Useful work.
     */
    private void doWork() {
        System.out.printf("%s: Useful work!%n", Thread.currentThread().getName());
    }
}

package ru.job4j.structures;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CASCount.
 * Non-blocking counter.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 05.12.2020
 */
@ThreadSafe
public class CASCount {
    /** Counter. */
    private final AtomicInteger count = new AtomicInteger(0);

    /**
     * Increases counter by one.
     */
    public void increment() {
        int oldValue;
        int newValue;
        do {
            Thread.yield();
            oldValue = count.get();
            newValue = oldValue + 1;
        } while (!count.compareAndSet(oldValue, newValue));
    }

    /**
     * Returns the counter value.
     * @return the counter value.
     */
    public int get() {
        return count.get();
    }
}

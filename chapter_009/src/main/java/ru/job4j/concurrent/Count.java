package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Count.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 02.06.2020
 */
@ThreadSafe
public class Count {
    /**
     * Counter value.
     */
    @GuardedBy("this")
    private int value;

    /**
     * Increments the counter by one.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Returns counter value.
     * @return counter value.
     */
    public synchronized int get() {
        return this.value;
    }
}

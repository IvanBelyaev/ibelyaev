package ru.job4j.structures;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * SimpleBlockingQueue.
 *
 * @param <T> the type of the elements of this queue.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 02.12.2020
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /** Storage. */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    /** Maximum capacity. */
    private final int capacity;

    /**
     * Constructor.
     * @param capacity - maximum capacity.
     */
    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Put an item in the queue.
     * This method blocks thread if queue is full.
     * @param value - new element.
     */
    public synchronized void offer(T value) {
        try {
            while (queue.size() == capacity) {
                wait();
            }
            queue.offer(value);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extract an item from the queue.
     * The method blocks thread if queue is empty.
     * @return first item from the queue.
     */
    public synchronized T poll() {
        T result = null;
        try {
            while (queue.size() == 0) {
                wait();
            }
            result = queue.poll();
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

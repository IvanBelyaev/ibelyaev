package ru.job4j.tracker;

/**
 * Observe.
 * @param <T> type of elements.
 */
public interface Observe<T> {
    /**
     * The method consumes an element.
     * @param model - an element.
     */
    void receive(T model);
}

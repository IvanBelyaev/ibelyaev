package ru.job4j.tracker;

import java.util.List;

/**
 * ITracker.
 * @author Ivan Belyaev
 * @since 03.11.2019
 * @version 2.0
 */
public interface ITracker {
    /**
     * The method adds a request.
     * @param item - new application.
     * @return returns a reference to the request or null if add failed.
     */
    Item add(Item item);

    /**
     * The method updates the information in the application.
     * @param id - id of request you want to update
     * @param item - new information for applications.
     */
    void replace(String id, Item item);

    /**
     * Method removes the application.
     * @param id - id of request you want to delete
     */
    void delete(String id);

    /**
     * The method returns all applications.
     * @return returns an array of all applications.
     */
    List<Item> findAll();

    /**
     * The method returns all applications with a specific name.
     * @param key - name to search for.
     * @return returns an array of all applications with a specific name.
     */
    List<Item> findByName(String key);

    /**
     * Method returns the request with a specific ID.
     * @param id - specific ID.
     * @return returns the request with a specific ID.
     */
    Item findById(String id);
}

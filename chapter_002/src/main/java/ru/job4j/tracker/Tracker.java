package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Tracker.
 * @author Ivan Belyaev
 * @since 14.09.2017
 * @version 1.0
 */
public class Tracker {
    /** Field to create a unique identifier for each request. */
    private static final Random RN = new Random();
    /** The maximum number of applications. */
    private static final int MAX_ITEMS = 100;

    /** Array of storage applications. */
    private Item[] items = new Item[MAX_ITEMS];
    /** Request counter. */
    private int itemsCounter = 0;

    /**
     * The method adds a request.
     * @param item - new application.
     * @return returns a reference to the request or null if add failed.
     */
    public Item add(Item item) {
        if (itemsCounter < MAX_ITEMS) {
            item.setId(this.generateId());
            items[itemsCounter++] = item;
        } else {
            item = null;
        }
        return item;
    }

    /**
     * The method updates the information in the application.
     * @param item - new information for applications.
     */
    public void update(Item item) {
        String id = item.getId();
        for (int index = 0; index < itemsCounter; index++) {
            if (items[index].getId().equals(id)) {
                items[index] = item;
                break;
            }
        }
    }

    /**
     * Method removes the application.
     * @param item - the application you want to delete.
     */
    public void delete(Item item) {
        String id = item.getId();
        for (int index = 0; index < itemsCounter; index++) {
            if (items[index].getId().equals(id)) {
                System.arraycopy(items, index + 1, items, index, itemsCounter - index - 1);
                itemsCounter--;
                break;
            }
        }
    }

    /**
     * The method returns all applications.
     * @return returns an array of all applications.
     */
    public Item[] findAll() {
        Item[] result = new Item[itemsCounter];
        for (int index = 0; index < itemsCounter; index++) {
            result[index] = items[index];
        }
        return result;
    }

    /**
     * The method returns all applications with a specific name.
     * @param key - name to search for.
     * @return returns an array of all applications with a specific name.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[itemsCounter];
        int resultIndex = 0;
        for (int index = 0; index < itemsCounter; index++) {
            if (items[index].getName().equals(key)) {
                result[resultIndex++] = items[index];
            }
        }
        return Arrays.copyOf(result, resultIndex);
    }

    /**
     * Method returns the request with a specific ID.
     * @param id - specific ID.
     * @return returns the request with a specific ID.
     */
    public Item findById(String id) {
        Item item = null;
        for (int index = 0; index < itemsCounter; index++) {
            if (items[index].getId().equals(id)) {
                item = items[index];
                break;
            }
        }
        return item;
    }

    /**
     * The method generates an ID.
     * @return returns the unique ID.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}

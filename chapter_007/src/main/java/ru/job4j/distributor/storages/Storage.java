package ru.job4j.distributor.storages;

import ru.job4j.distributor.products.Food;

import java.util.Set;

/**
 * Storage.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public interface Storage {
    /**
     * The method adds the product to the store.
     * @param food any product.
     */
    void add(Food food);

    /**
     * The method checks if the food is suitable for this storage.
     * @param food any product.
     * @return true if suitable for the given storage; otherwise returns false.
     */
    boolean accept(Food food);

    /**
     * The method returns a set of products in the store.
     * @return set of products in the store.
     */
    Set<Food> getProducts();

    /**
     * The method retrieves all products from the store.
     * Cleans storage.
     * @return a set of products that was in the store.
     */
    Set<Food> extractProducts();
}

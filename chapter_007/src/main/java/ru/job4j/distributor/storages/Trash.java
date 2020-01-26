package ru.job4j.distributor.storages;

import ru.job4j.distributor.products.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

/**
 * Storage.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public class Trash implements Storage {
    /** Storage. */
    private Set<Food> products = new HashSet<>();

    /**
     * The method adds the product to the store.
     * @param food any product.
     */
    @Override
    public void add(Food food) {
        products.add(food);
    }

    /**
     * The method checks if the food is suitable for this storage.
     * @param food any product.
     * @return true if suitable for the given storage; otherwise returns false.
     */
    @Override
    public boolean accept(Food food) {
        boolean result = false;
        long fullShelfLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long passedFromCreateDate  = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        double expirationPercentage = (double) passedFromCreateDate / fullShelfLife * 100.0;
        if (expirationPercentage >= 100) {
            products.add(food);
            result = true;
        }
        return result;
    }

    /**
     * The method returns a set of products in the store.
     * @return set of products in the store.
     */
    @Override
    public Set<Food> getProducts() {
        return products;
    }

    /**
     * The method retrieves all products from the store.
     * Cleans storage.
     * @return a set of products that was in the store.
     */
    @Override
    public Set<Food> extractProducts() {
        Set<Food> extractedProducts = new HashSet<>(getProducts());
        products.clear();
        return extractedProducts;
    }
}

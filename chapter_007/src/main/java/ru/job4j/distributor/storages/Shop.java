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
public class Shop implements Storage {
    /** Storage. */
    private Set<Food> products = new HashSet<>();

    /**
     * The method adds the product to the store.
     * @param food any product.
     */
    @Override
    public void add(Food food) {
        double expirationPercentage = getExpirationPercentage(food);
        if (expirationPercentage >= 25 && expirationPercentage <= 75) {
            products.add(food);
        } else if (expirationPercentage > 75 && expirationPercentage < 100) {
            food.setDiscount(50);
            products.add(food);
        }
    }

    /**
     * The method checks if the food is suitable for this storage.
     * @param food any product.
     * @return true if suitable for the given storage; otherwise returns false.
     */
    @Override
    public boolean accept(Food food) {
        boolean result = false;
        double expirationPercentage = getExpirationPercentage(food);
        if (expirationPercentage >= 25 && expirationPercentage < 100) {
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
     * Method returns expiration percentage of the product.
     * @param food any product.
     * @return expiration percentage of the product.
     */
    private double getExpirationPercentage(Food food) {
        long fullShelfLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long passedFromCreateDate  = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        return (double) passedFromCreateDate / fullShelfLife * 100.0;
    }
}

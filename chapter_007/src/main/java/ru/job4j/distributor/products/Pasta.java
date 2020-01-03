package ru.job4j.distributor.products;

import java.time.LocalDate;

/**
 * Pasta.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public class Pasta extends Food {
    /**
     * Constructor for Pasta.
     * @param name the product's name.
     * @param expiryDate expiry date.
     * @param createDate production date.
     * @param price price.
     * @param discount discount.
     */
    public Pasta(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}

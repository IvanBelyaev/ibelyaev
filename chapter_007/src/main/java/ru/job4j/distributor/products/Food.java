package ru.job4j.distributor.products;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Food.
 * Abstract class for all products.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public abstract class Food {
    /** The product's name. */
    private final String name;
    /** Expiry date. */
    private final LocalDate expiryDate;
    /** Production date. */
    private final LocalDate createDate;
    /** Price. */
    private double price;
    /** Discount. */
    private double discount;

    /**
     * Constructor for all kinds of products.
     * @param name the product's name.
     * @param expiryDate expiry date.
     * @param createDate production date.
     * @param price price.
     * @param discount discount.
     */
    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    /**
     * The method sets discount.
     * @param discount discount.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * The method returns the product's name.
     * @return the product's name.
     */
    public String getName() {
        return name;
    }

    /**
     * The method returns expiry date.
     * @return expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * The method returns production date.
     * @return production date.
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * The method returns price.
     * @return price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * The method returns discount.
     * @return discount.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * The method checks objects for equality.
     * @param o the object with which to compare this object.
     * @return true if the objects are the same otherwise returns false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && Double.compare(food.discount, discount) == 0
                && name.equals(food.name)
                && expiryDate.equals(food.expiryDate)
                && createDate.equals(food.createDate);
    }

    /**
     * The method returns a hash code for this object.
     * @return returns a hash code for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }
}

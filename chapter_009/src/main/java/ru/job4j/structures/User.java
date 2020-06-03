package ru.job4j.structures;

/**
 * User.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 03.06.2020
 */
public class User {
    /** ID. */
    private final int id;
    /** Amount of money. */
    private int amount;

    /**
     * Constructor.
     * @param id id.
     * @param amount amount of money.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Returns amount of money.
     * @return amount of money.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount of money.
     * @param amount new amount of money.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns user id.
     * @return user id.
     */
    public int getId() {
        return id;
    }
}

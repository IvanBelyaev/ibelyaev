package ru.job4j.mail;

/**
 * User.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 17.12.2020
 */
public class User {
    /** Username. */
    private final String userName;
    /** Email. */
    private final String email;

    /**
     * Constructor.
     * @param userName username.
     * @param email email.
     */
    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    /**
     * Returns username.
     * @return username.
     */
    public String getName() {
        return userName;
    }

    /**
     * Returns email.
     * @return email.
     */
    public String getEmail() {
        return email;
    }
}

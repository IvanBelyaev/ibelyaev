package ru.job4j.map;

import java.util.Calendar;

/**
 * User.
 * @author Ivan Belyaev
 * @since 13.10.2018
 * @version 1.0
 */
public class User {
    /** User name. */
    private String name;
    /** Number of children. */
    private int children;
    /** Birthday. */
    private Calendar birthday;

    /**
     * The constructor creates the object User.
     * @param name - user name.
     * @param children - number of children.
     * @param birthday - birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return name;
    }
}

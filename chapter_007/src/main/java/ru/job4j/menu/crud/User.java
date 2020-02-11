package ru.job4j.menu.crud;

/**
 * User.
 * @author Ivan Belyaev
 * @since 24.01.2020
 * @version 2.0
 */
public class User {
    /** ID. */
    private final int id;
    /** Username. */
    private String name;
    /** user age. */
    private int age;

    /**
     * Constructor.
     * @param id id.
     * @param name username.
     * @param age user age.
     */
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * Method returns username.
     * @return username.
     */
    public String getName() {
        return name;
    }

    /**
     * Method sets username.
     * @param name new username.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method returns user age.
     * @return user age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Method sets user age.
     * @param age new user age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method returns user id.
     * @return user id.
     */
    public int getId() {
        return id;
    }

    /**
     * The method returns string to display the user.
     * @return string to display the user.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}

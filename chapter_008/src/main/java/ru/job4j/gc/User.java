package ru.job4j.gc;

/**
 * User.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 04.03.2020
 */
public class User {
    /** Username. */
    private String name;
    /** User age. */
    private int age;

    /**
     * Constructor.
     * @param name username.
     * @param age user age.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns username.
     * @return username.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets username.
     * @param name new username.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns user age.
     * @return user age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets user age.
     * @param age new user age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Converts object to String.
     * @return
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    /**
     * Performs finalization.
     * @throws Throwable exceptions.
     */
    @Override
    protected void finalize() throws Throwable {
        System.err.printf("User %s deleted.%s", this, System.getProperty("line.separator"));
    }
}

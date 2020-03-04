package ru.job4j.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * TestGC.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 04.03.2020
 */
public class TestGC {
    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10_000; i++) {
                users.add(new User("John", i % 80));
            }
            for (int i = 3_000; i < 10_000; i++) {
                users.remove(3_000);
            }
        }
    }
}

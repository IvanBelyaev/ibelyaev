package ru.job4j.compare;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * SortUser.
 * @author Ivan Belyaev
 * @since 11.01.2018
 * @version 1.0
 */
public class SortUser {
    /**
     * The method returns sorted Set<User>.
     * @param list - the list of users.
     * @return returns sorted Set<User>.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}

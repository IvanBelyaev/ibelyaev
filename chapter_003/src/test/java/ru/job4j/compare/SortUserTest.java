package ru.job4j.compare;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SortUserTest.
 * @author Ivan Belyaev
 * @since 11.01.2018
 * @version 1.0
 */
public class SortUserTest {
    /**
     * Test for sort method.
     */
    @Test
    public void whenSortListOfUsersThenSortedSet() {
        SortUser sortUser = new SortUser();

        User anna = new User("Anna", 23);
        User denis = new User("Denis", 21);
        User sergey = new User("Sergey", 25);

        List<User> list = new ArrayList<>();
        list.add(anna);
        list.add(denis);
        list.add(sergey);

        Set<User> methodReturns = sortUser.sort(list);

        Set<User> expected = new LinkedHashSet<>();
        expected.add(denis);
        expected.add(anna);
        expected.add(sergey);

        assertThat(methodReturns, is(expected));
    }
}

package ru.job4j.structures;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SingleLockListTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 08.06.2020
 */
public class SingleLockListTest {
    /**
     * Simple test.
     * @throws InterruptedException exceptions.
     */
    @Test
    public void whenAddsTwoElementsIntoListThenListHasTheSameTwoElements() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }
}
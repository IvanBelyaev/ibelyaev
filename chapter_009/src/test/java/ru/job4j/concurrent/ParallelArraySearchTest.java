package ru.job4j.concurrent;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ParallelArraySearchTest.
 * @author Ivan Belyaev
 * @since 06.06.2021
 * @version 1.0
 */
public class ParallelArraySearchTest {
    /**
     * Test when element exists.
     */
    @Test
    public void whenArrayOfIntegerHasTheNumberThenReturnsIndexOfTheNumber() {
        Integer[] arr = {1, 2, 5, 9, 3, 4, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9};
        int elem = 4;

        int index = ParallelArraySearch.find(arr, elem);

        assertThat(index, is(5));
    }

    /**
     * Test when element doesn't exist.
     */
    @Test
    public void whenArrayOfIntegerHasNotTheNubmerThenReturnsMinusOne() {
        Integer[] arr = {1, 2, 5, 9, 3, 4, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9};
        int elem = 10;

        int index = ParallelArraySearch.find(arr, elem);

        assertThat(index, is(-1));
    }

    /**
     * Test when the element is last in the array.
     */
    @Test
    public void whenArrayOfIntegerHasTheNumberAndNumberIsLastThenReturnsIndexOfTheNumber() {
        Integer[] arr = {1, 2, 5, 9};
        int elem = 9;

        int index = ParallelArraySearch.find(arr, elem);

        assertThat(index, is(3));
    }
}

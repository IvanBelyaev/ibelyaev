package ru.job4j.structures;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CASCountTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 05.12.2020
 */
public class CASCountTest {
    /**
     * Test.
     * @throws InterruptedException possible exception.
     */
    @Test
    public void whenTwoThreadIncreaseCountThenCounterIncreasedTwice() throws InterruptedException {
        CASCount count = new CASCount();
        Runnable runnable = () -> {
            IntStream.range(0, 100).forEach(i -> count.increment());
        };
        Thread first = new Thread(runnable);
        Thread second = new Thread(runnable);
        first.start();
        second.start();
        first.join();
        second.join();

        assertThat(count.get(), is(200));
    }
}

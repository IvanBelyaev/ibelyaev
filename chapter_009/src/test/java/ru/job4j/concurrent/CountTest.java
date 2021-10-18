package ru.job4j.concurrent;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * CountTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 02.06.2020
 */
public class CountTest {

    /**
     * Класс описывает нить со счетчиком.
     */
    private class ThreadCount extends Thread {
        /** Counter. */
        private final Count count;

        /**
         * Constructor.
         * @param count counter.
         */
        private ThreadCount(final Count count) {
            this.count = count;
        }

        /**
         * Increments the counter by one.
         */
        @Override
        public void run() {
            this.count.increment();
        }
    }

    /**
     * Simple multithreaded class checking.
     * @throws InterruptedException exceptions.
     */
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }
}

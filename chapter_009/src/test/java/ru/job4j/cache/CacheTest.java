package ru.job4j.cache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * CacheTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 14.12.2020
 */
public class CacheTest {
    /**
     * Test when multiple threads are working with the cache.
     * @throws InterruptedException - possible exception.
     */
    @Test
    public void whenTwoThreadsUpdateElementThenElementIsUpdatedTwice() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Base model = new Base(123, 0);
        Cache cache = new Cache();
        cache.add(model);
        Runnable runnable = () -> {
            try {
                IntStream.range(0, 1_000).forEach(i -> cache.update(model));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        };
        Thread first = new Thread(runnable);
        Thread second = new Thread(runnable);

        first.start();
        second.start();
        first.join();
        second.join();

        assertThat(model.getVersion(), is(2_000));
        assertNull(ex.get());
    }

    /**
     * Test when an element with different versions is updated.
     * @throws InterruptedException - possible exception.
     */
    @Test
    public void whenElementUpdatesWithNotEqualVersionThenOptimisticException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Base model = new Base(123, 0);
        Cache cache = new Cache();
        cache.add(model);
        Runnable runnable = () -> {
            try {
                cache.update(new Base(123, 1));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        };
        Thread first = new Thread(runnable);

        first.start();
        first.join();

        assertThat(ex.get().getMessage(), is("Object versions don't match."));
    }
}

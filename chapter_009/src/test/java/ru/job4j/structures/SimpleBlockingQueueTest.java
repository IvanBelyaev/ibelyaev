package ru.job4j.structures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SimpleBlockingQueueTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 02.12.2020
 */
public class SimpleBlockingQueueTest {
    /**
     * Test for SimpleBlockingQueue.
     * @throws InterruptedException - possible exception.
     */
    @Test
    public void whenThreeElementsOffersThenThreeElementsPolls() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(2);
        List<Integer> result = new ArrayList<>();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                sbq.offer(i);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    result.add(sbq.poll());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        assertThat(result, is(Arrays.asList(0, 1, 2)));
    }

    /**
     * Test when consumer was interrupted.
     * @throws InterruptedException possible exception.
     */
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        List<Integer> result = new CopyOnWriteArrayList<>();
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            IntStream.range(0, 5).forEach(i -> sbq.offer(i * i));
        });
        Thread consumer = new Thread(() -> {
           while (!sbq.isEmpty() || !Thread.currentThread().isInterrupted()) {
               try {
                   result.add(sbq.poll());
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.interrupt();
        consumer.join();

        assertThat(result, is(Arrays.asList(0, 1, 4, 9, 16)));
    }
}

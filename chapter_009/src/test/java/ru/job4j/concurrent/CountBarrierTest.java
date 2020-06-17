package ru.job4j.concurrent;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CountBarrierTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 08.06.2020
 */
public class CountBarrierTest {
    /**
     * Test that until the counter reaches a maximum the sleeping Thread will not wake up.
     * @throws InterruptedException exceptions.
     */
    @Test
    public void whenThreadWaitedThenFirstCounterBecomesEqualToTotalThenThreadWakesUp() throws InterruptedException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        int total = 10_000_000;
        CountBarrier countBarrier = new CountBarrier(total);
        Thread waitedThread = new Thread(() -> countBarrier.await(), "waitedThread");
        Thread changingCounter = new Thread(
                () -> {
                    for (int i = 0; i < total + 1; i++) {
                        countBarrier.count();
                    }
                },
                "changingCounter"
        );

        waitedThread.start();
        changingCounter.start();
        waitedThread.join();
        changingCounter.join();

        String[] outputStrings = out.toString().split(System.getProperty("line.separator"));

        assertThat(outputStrings[0], is("waitedThread: Useful work!"));
    }
}

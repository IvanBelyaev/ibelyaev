package ru.job4j.parking.cars;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * KamazTest.
 * @author Ivan Belyaev
 * @since 21.01.2020
 * @version 2.0
 */
public class KamazTest {
    /**
     * Test.
     */
    @Test
    public void whenKamazGetSizeThenMethodReturnsOne() {
        Car kamaz = new Kamaz();
        assertThat(kamaz.getSize(), is(3));
    }
}

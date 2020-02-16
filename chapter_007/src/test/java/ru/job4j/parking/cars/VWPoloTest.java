package ru.job4j.parking.cars;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * VWPoloTest.
 * @author Ivan Belyaev
 * @since 21.01.2020
 * @version 2.0
 */
public class VWPoloTest {
    /**
     * Test.
     */
    @Test
    public void whenPoloGetSizeThenMethodReturnsOne() {
        Car polo = new VWPolo();
        assertThat(polo.getSize(), is(1));
    }
}

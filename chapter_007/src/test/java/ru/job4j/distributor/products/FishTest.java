package ru.job4j.distributor.products;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * FishTest.
 * Class for testing class Fish.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public class FishTest {
    /**
     * Test for the Fish class.
     */
    @Test
    public void testForFish() {
        Food fish = new Fish(
                "fish",
                LocalDate.of(2020, 01, 31),
                LocalDate.of(2019, 11, 30),
                300,
                20
        );

        assertThat(fish.getName(), is("fish"));
        assertThat(fish.getExpiryDate(), is(LocalDate.of(2020, 01, 31)));
        assertThat(fish.getCreateDate(), is(LocalDate.of(2019, 11, 30)));
        assertThat(fish.getPrice(), is(300.0));
        assertThat(fish.getDiscount(), is(20.0));
        fish.setDiscount(30);
        assertThat(fish.getDiscount(), is(30.0));
    }
}

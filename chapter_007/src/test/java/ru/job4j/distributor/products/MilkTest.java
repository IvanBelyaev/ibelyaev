package ru.job4j.distributor.products;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MilkTest.
 * Class for testing class Milk.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public class MilkTest {
    /**
     * Test for the Milk class.
     */
    @Test
    public void testForMilk() {
        Food milk = new Milk(
                "milk",
                LocalDate.of(2020, 01, 31),
                LocalDate.of(2019, 11, 30),
                300,
                20
        );

        assertThat(milk.getName(), is("milk"));
        assertThat(milk.getExpiryDate(), is(LocalDate.of(2020, 01, 31)));
        assertThat(milk.getCreateDate(), is(LocalDate.of(2019, 11, 30)));
        assertThat(milk.getPrice(), is(300.0));
        assertThat(milk.getDiscount(), is(20.0));
        milk.setDiscount(30);
        assertThat(milk.getDiscount(), is(30.0));
    }
}

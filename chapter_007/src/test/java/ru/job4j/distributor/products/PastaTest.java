package ru.job4j.distributor.products;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PastaTest.
 * Class for testing class Pasta.
 * @author Ivan Belyaev
 * @since 28.12.2019
 * @version 2.0
 */
public class PastaTest {
    /**
     * Test for the Pasta class.
     */
    @Test
    public void testForPasta() {
        Food pasta = new Pasta(
                "pasta",
                LocalDate.of(2020, 01, 31),
                LocalDate.of(2019, 11, 30),
                300,
                20
        );

        assertThat(pasta.getName(), is("pasta"));
        assertThat(pasta.getExpiryDate(), is(LocalDate.of(2020, 01, 31)));
        assertThat(pasta.getCreateDate(), is(LocalDate.of(2019, 11, 30)));
        assertThat(pasta.getPrice(), is(300.0));
        assertThat(pasta.getDiscount(), is(20.0));
        pasta.setDiscount(30);
        assertThat(pasta.getDiscount(), is(30.0));
    }
}

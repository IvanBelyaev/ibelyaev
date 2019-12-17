package ru.job4j.parser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * VacancyTest.
 * @author Ivan Belyaev
 * @since 17.12.2019
 * @version 1.0
 */
public class VacancyTest {
    /**
     * Test for the Vacancy class.
     */
    @Test
    public void whenVacancyIsCreatedItContainsNameTextAndLink() {
        Vacancy vacancy = new Vacancy("name", "text", "link");
        assertThat(vacancy.getName(), is("name"));
        assertThat(vacancy.getText(), is("text"));
        assertThat(vacancy.getLink(), is("link"));
    }
}

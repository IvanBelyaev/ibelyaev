package ru.job4j.parser;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * DateParserTest.
 * @author Ivan Belyaev
 * @since 17.12.2019
 * @version 1.0
 */
public class DateParserTest {
    /**
     * Test for the DateParser class.
     */
    @Test
    public void whenParseDateThenGetTheSameDate() {
        LocalDateTime methodReturns = new DateParser().parseDate("14 дек 19, 20:14");
        assertThat(methodReturns, is(LocalDateTime.of(2019, 12, 14, 20, 14)));
        methodReturns = new DateParser().parseDate("сегодня, 14:32");
        assertThat(methodReturns, is(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 32))));
        methodReturns = new DateParser().parseDate("вчера, 14:32");
        assertThat(methodReturns, is(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(14, 32))));
    }
}

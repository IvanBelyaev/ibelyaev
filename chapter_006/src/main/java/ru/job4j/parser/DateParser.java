package ru.job4j.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

/**
 * DateParser.
 * Date parsing class with sql.ru
 * @author Ivan Belyaev
 * @since 15.12.2019
 * @version 2.0
 */
public class DateParser {
    /** Map to convert months to values. */
    private Map<Long, String> monthNameMap = new HashMap<>();
    /** DateTimeFormatter for parsing dates. */
    private DateTimeFormatter dateTimeFormatter;

    /**
     * The constructor creates DateParser.
     */
    public DateParser() {
        this.init();
        this.dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("d ")
                .appendText(ChronoField.MONTH_OF_YEAR, monthNameMap)
                .appendPattern(" yy, HH:mm")
                .toFormatter();
    }

    /**
     * The method fills the map to convert months to values.
     */
    private void init() {
        monthNameMap.put(1L, "янв");
        monthNameMap.put(2L, "фев");
        monthNameMap.put(3L, "мар");
        monthNameMap.put(4L, "апр");
        monthNameMap.put(5L, "май");
        monthNameMap.put(6L, "июн");
        monthNameMap.put(7L, "июл");
        monthNameMap.put(8L, "авг");
        monthNameMap.put(9L, "сен");
        monthNameMap.put(10L, "окт");
        monthNameMap.put(11L, "ноя");
        monthNameMap.put(12L, "дек");
    }

    /**
     * Method converts String to LocalDateTime.
     * @param dateTimeString - date string from sql.ru.
     * @return date in LocalDateTime format.
     */
    public LocalDateTime parseDate(String dateTimeString) {
        LocalDateTime result;
        String[] dateTimeArray = dateTimeString.split(",");
        String dateString = dateTimeArray[0];
        String[] timeArray = dateTimeArray[1].trim().split(":");
        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        if ("сегодня".equals(dateString)) {
            result = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusHours(hours).plusMinutes(minutes);
        } else if ("вчера".equals(dateString)) {
            result = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).minusDays(1).plusHours(hours).plusMinutes(minutes);
        } else {
            result = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        }
        return result;
    }
}

package ru.job4j.generator;

import java.util.Map;

/**
 * Generator.
 * @author Ivan Belyaev
 * @since 28.01.2020
 * @version 2.0
 */
public interface Generator {
    /**
     * The method receives an input string with keys in the text and a map with values for these keys
     * and generates a string with these values.
     * @param template input string with keys in the text.
     * @param map map with values for the keys.
     * @return string with corresponding values.
     */
    String generate(String template, Map<String, String> map);
}

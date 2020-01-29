package ru.job4j.generator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SimpleGenerator.
 * @author Ivan Belyaev
 * @since 28.01.2020
 * @version 2.0
 */
public class SimpleGenerator implements Generator {
    /** Key search template. */
    private static final Pattern KEYS = Pattern.compile("\\$\\{\\w+\\}");

    /**
     * The method receives an input string with keys in the text and a map with values for these keys
     * and generates a string with these values.
     * @param template input string with keys in the text.
     * @param map map with values for the keys.
     * @return string with corresponding values.
     */
    @Override
    public String generate(String template, Map<String, String> map) {
        Matcher matcher = KEYS.matcher(template);
        StringBuilder sb = new StringBuilder();
        Set<String> usedKeys = new HashSet<>();
        while (matcher.find()) {
            String foundString = matcher.group();
            String key = foundString.substring(2, foundString.length() - 1);
            usedKeys.add(key);
            String value = map.get(key);
            if (value != null) {
                matcher.appendReplacement(sb, value);
            } else {
                throw new IllegalArgumentException("Missing key - " + key);
            }
        }
        matcher.appendTail(sb);
        if (map.size() != usedKeys.size()) {
            throw new IllegalArgumentException("There are unused keys.");
        }
        return sb.toString();
    }
}

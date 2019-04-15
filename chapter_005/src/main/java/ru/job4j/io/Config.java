package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Config.
 * @author Ivan Belyaev
 * @since 14.04.2019
 * @version 1.0
 */
public class Config {
    /** Configuration file path. */
    private final String path;
    /** Settings from the configuration file. */
    private final Map<String, String> values = new HashMap<>();

    /**
     * The constructor creates the object Config.
     * @param path - configuration file path.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Method loads settings from configuration file.
     */
    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !s.startsWith("#"))
                    .filter(s -> s.contains("="))
                    .forEach(s -> {
                        int index = s.indexOf("=");
                        values.put(s.substring(0, index), s.substring(index + 1));
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method returns the value of the setting parameter by its name.
     * @param key - setting name.
     * @return returns the value of the setting parameter by its name; if the parameter is not found, returns null.
     */
    public String value(String key) {
        return values.get(key);
    }
}

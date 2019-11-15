package ru.job4j.magnit;

import java.io.InputStream;
import java.util.Properties;

/**
 * Config.
 * @author Ivan Belyaev
 * @since 15.11.2019
 * @version 2.0
 */
public class Config {
    /** Settings for connecting to the database. */
    private final Properties values = new Properties();

    /**
     * The constructor creates the object Config.
     */
    public Config() {
        init();
    }

    /**
     * Method loads settings for connecting to the database..
     */
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("magnit.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Method returns property by key.
     * @param key - property name.
     * @return the value in this property list with the specified key value.
     */
    public String get(String key) {
        return this.values.getProperty(key);
    }
}

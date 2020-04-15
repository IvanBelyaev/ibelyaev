package ru.job4j.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UsageLog4j.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 15.04.2020
 */
public class UsageLog4j {
    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        boolean bool = true;
        char ch = 'a';
        byte b = 1;
        short sh = 2;
        int i = 12;
        long ll = 112;
        float f = 0.0f;
        double d = 8.8;

        LOG.debug("boolean bool = {}", bool);
        LOG.debug("char ch = {}, byte b = {}", ch, b);
        LOG.debug("short sh = {}, int i = {}, long ll = {}", sh, i, ll);
        LOG.debug("float f = {}, double d = {}", f, d);
    }
}

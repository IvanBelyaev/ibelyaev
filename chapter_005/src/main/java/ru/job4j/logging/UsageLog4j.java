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
    }
}

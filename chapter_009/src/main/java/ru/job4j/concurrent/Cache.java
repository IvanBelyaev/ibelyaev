package ru.job4j.concurrent;

/**
 * Cache.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 19.05.2020
 */
public final class Cache {
    /** Cache. */
    private static Cache cache;

    /**
     * Returns a single Cache object common to all.
     * @return a single Cache object common to all.
     */
    private static synchronized Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}

package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 07.12.2020
 */
public class Cache {
    /** Cache. */
    private Map<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * Adds a new element to the cache.
     * @param model new element.
     */
    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    /**
     * Updates element.
     * If the versions of the elements do not match throws OptimisticException.
     * @param model element to be updated
     */
    public void update(Base model) {
        cache.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != value.getVersion()) {
                throw new OptimisticException("Object versions don't match.");
            }
            value.setVersion(value.getVersion() + 1);
            return value;
        });
    }

    /**
     * Deletes element.
     * @param model element to be deleted.
     */
    public void delete(Base model) {
        cache.remove(model.getId());
    }
}

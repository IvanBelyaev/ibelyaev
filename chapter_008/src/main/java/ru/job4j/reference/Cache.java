package ru.job4j.reference;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cache.
 * use -XX:SoftRefLRUPolicyMSPerMB=0
 * @author Ivan Belyaev
 * @version 1.0
 * @since 17.04.2020
 */
public class Cache {
    /** Cache. */
    private Map<String, SoftReference<List<String>>> cache = new HashMap<>();

    /**
     * The method returns the contents of the file.
     * If the file is in the cache, then the data is taken from the cache,
     * if not, that data is loaded into the cache.
     * @param fileName file name.
     * @return the contents of the file.
     * @throws IOException input / output exceptions.
     */
    public List<String> getDataFrom(String fileName) throws IOException {
        SoftReference<List<String>> fileData = cache.get(fileName);
        if (fileData == null || fileData.get() == null) {
            cache.put(fileName, new SoftReference<>(readFile(fileName)));
        }
        return cache.get(fileName).get();
    }

    /**
     * The method reads the contents of the file from disk.
     * @param name file name.
     * @return the contents of the file from disk.
     * @throws IOException input / output exceptions.
     */
    private List<String> readFile(String name) throws IOException {
        return Files.readAllLines(Path.of(name));
    }
}

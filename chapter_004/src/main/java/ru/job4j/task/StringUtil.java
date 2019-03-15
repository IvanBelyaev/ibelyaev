package ru.job4j.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * StringUtil.
 * @author Ivan Belyaev
 * @since 09.03.2019
 * @version 1.0
 */
public class StringUtil {
    /**
     * The method searches for all duplicate characters in the string.
     * @param str - the string.
     * @return repeated characters in string.
     */
    public Set<Character> findDuplicates(String str) {
        HashMap<Character, Integer> allLetters = new HashMap<>();
        HashSet<Character> duplicates = new HashSet<>();

        for (char ch : str.toCharArray()) {
            allLetters.computeIfPresent(ch, (k, v) -> { duplicates.add(ch); v = 2; return v; });
            allLetters.putIfAbsent(ch, 1);
        }

        return duplicates;
    }
}

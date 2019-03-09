package ru.job4j.task;

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
        Set<Character> allLetters = new HashSet<>();
        Set<Character> duplicates = new HashSet<>();

        for (Character ch : str.toCharArray()) {
            if (allLetters.contains(ch)) {
                duplicates.add(ch);
            } else {
                allLetters.add(ch);
            }
        }

        return duplicates;
    }
}

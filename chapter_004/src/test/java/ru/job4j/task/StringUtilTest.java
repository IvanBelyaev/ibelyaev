package ru.job4j.task;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * StringUtilTest.
 * @author Ivan Belyaev
 * @since 09.03.2019
 * @version 1.0
 */
public class StringUtilTest {
    /**
     * First test for the findDuplicates method.
     */
    @Test
    public void whenTheStringDoesNotHaveDuplicatesThenfindDuplicatesReturnsEmptySet() {
        StringUtil stringUtil = new StringUtil();
        Set<Character> methodReturns = stringUtil.findDuplicates("abc");
        Set<Character> expected = new HashSet<>();

        assertThat(methodReturns, is(expected));
    }

    /**
     * Second test for the findDuplicates method.
     */
    @Test
    public void whenTheStringHasDuplicatesThenfindDuplicatesReturnsDuplicates() {
        StringUtil stringUtil = new StringUtil();
        Set<Character> methodReturns = stringUtil.findDuplicates("abcadebf");
        Set<Character> expected = new HashSet<>();
        expected.add('b');
        expected.add('a');

        assertThat(methodReturns, is(expected));
    }
}

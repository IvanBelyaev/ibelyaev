package ru.job4j.generator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SimpleGeneratorTest.
 * @author Ivan Belyaev
 * @since 28.01.2020
 * @version 2.0
 */
public class SimpleGeneratorTest {
    /**
     * Test when a line has two keys, and a map has two of the same keys.
     */
    @Test
    public void whenGenerateGetsTwoKeysAndMapWithTheSameKeysThenGeneratesStringWithValuesOfTheseKeys() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Ivan");
        keys.put("subject", "you");
        String expectedString = "I am a Ivan, Who are you?";

        Generator generator = new SimpleGenerator();
        String methodReturns = generator.generate(template, keys);

        assertThat(methodReturns, is(expectedString));
    }

    /**
     * Test when the string has three identical keys, and the map contains this key alone.
     */
    @Test
    public void whenGenerateGetsStringWithThreeIdenticalKeysThenReturnsStringWithThreeIdenticalValueOfThisKey() {
        String template = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> keys = new HashMap<>();
        keys.put("sos", "Ааа");
        String expectedString = "Help, Ааа, Ааа, Ааа";

        Generator generator = new SimpleGenerator();
        String methodReturns = generator.generate(template, keys);

        assertThat(methodReturns, is(expectedString));
    }

    /**
     * Test when there are more keys in the map than in the input string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenGenerateGetsStringWithOneKeyAndMapWithTwoKeysThenThrowsException() {
        String template = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> keys = new HashMap<>();
        keys.put("sos", "Ааа");
        keys.put("subject", "you");
        String expectedString = "Help, Ааа, Ааа, Ааа";

        Generator generator = new SimpleGenerator();
        String methodReturns = generator.generate(template, keys);

        assertThat(methodReturns, is(expectedString));
    }

    /**
     * Test when the map does not contain all the keys that are on the input string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenGenerateGetsStringWithTwoKeysAndMapWithOneKeyThenThrowsException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = new HashMap<>();
        keys.put("subject", "you");
        String expectedString = "I am Ivan, Who are you?";

        Generator generator = new SimpleGenerator();
        String methodReturns = generator.generate(template, keys);

        assertThat(methodReturns, is(expectedString));
    }
}

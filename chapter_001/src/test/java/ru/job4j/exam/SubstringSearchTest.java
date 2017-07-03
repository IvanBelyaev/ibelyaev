package ru.job4j.exam;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SubstringSearchTest.
 * @author Ivan Belyaev
 * @since 03.07.2017
 * @version 1.0
 */
public class SubstringSearchTest {
	/**
	 * Test for the contains method.
	 */
	@Test
	public void whenContainsTheStringHasThisSubstringThenTrue() {
		SubstringSearch substringSearch = new SubstringSearch();
		boolean methodReturns = substringSearch.contains("Привет", "иве");
		boolean expected = true;
		assertThat(methodReturns, is(expected));
	}
}

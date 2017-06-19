package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BoardTest.
 * @author Ivan Belyaev
 * @since 19.06.2017
 * @version 1.0
 */
public class BoardTest {
	/**
	 * Test for the paint method, when 3x3.
	 */
	@Test
	public void whenPaintWidthThreeHeightThreeThenThreeOnThree() {
		Board board = new Board();
		String methodReturns = board.paint(3, 3);
		String expected = "x x\n x \nx x\n";
		assertThat(methodReturns, is(expected));
	}

	/**
	 * Test for the paint method, when 5x4.
	 */
	@Test
	public void whenPaintWidthFiveHeightFourThenFiveOnFour() {
		Board board = new Board();
		String methodReturns = board.paint(5, 4);
		String expected = "x x x\n x x \nx x x\n x x \n";
		assertThat(methodReturns, is(expected));
	}
}

package ru.job4j.exam;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CoffeeMachineTest.
 * @author Ivan Belyaev
 * @since 04.12.2017
 * @version 1.0
 */
public class CoffeeMachineTest {
    /**
     * The first test for the changes method.
     */
    @Test
    public void whenChangesFiftyAndThirtyFiveThenTwoCoins() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] methodReturns = coffeeMachine.changes(50, 35);
        int[] expected = {10, 5};
        assertThat(methodReturns, is(expected));
    }

    /**
     * The second test for the changes method.
     */
    @Test
    public void whenChangesFiftyAndTwentyTwoThenFiveCoins() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] methodReturns = coffeeMachine.changes(50, 22);
        int[] expected = {10, 10, 5, 2, 1};
        assertThat(methodReturns, is(expected));
    }

    /**
     * The third test for the changes method.
     */
    @Test
    public void whenChangesThirtyAndSixtyThenThreeCoins() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] methodReturns = coffeeMachine.changes(30, 60);
        int[] expected = {10, 10, 10};
        assertThat(methodReturns, is(expected));
    }
}

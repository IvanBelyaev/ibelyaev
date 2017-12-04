package ru.job4j.exam;

import java.util.ArrayList;

/**
 * CoffeeMachine.
 * @author Ivan Belyaev
 * @since 04.12.2017
 * @version 1.0
 */
public class CoffeeMachine {
    /** Available coins. */
    private int[] coins = {10, 5, 2, 1};

    /**
     * Method give change from a vending machine.
     * @param value - the amount of money invested in the machine.
     * @param price - the price of the goods.
     * @return returns an array of coins.
     */
    public int[] changes(int value, int price) {
        ArrayList<Integer> list = new ArrayList<>();

        int change;
        if (value - price > 0) {
            change = value - price;
        } else {
            change = value;
        }

        for (int i = 0; i < coins.length; i++) {
            while (change / coins[i] > 0) {
                list.add(coins[i]);
                change -= coins[i];
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}

package ru.job4j.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * RowColSumTest.
 */
public class RowColSumTest {
    /**
     * Test for matrix 1x1.
     * @throws ExecutionException possible exceptions.
     * @throws InterruptedException possible exceptions.
     */
    @Test
    public void whenMatrixSizeIsOne() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1}
        };
        RowColSum.Sums[] expected = new RowColSum.Sums[1];
        expected[0] = new RowColSum.Sums(1, 1);

        RowColSum.Sums[] methodSumReturned = RowColSum.sum(matrix);
        RowColSum.Sums[] methodAsyncSumReturned = RowColSum.asyncSum(matrix);

        assertThat(methodSumReturned, is(expected));
        assertThat(methodAsyncSumReturned, is(expected));
    }

    /**
     * Test for matrix 2x2.
     * @throws ExecutionException possible exceptions.
     * @throws InterruptedException possible exceptions.
     */
    @Test
    public void whenMatrixSizeIsTwo() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        RowColSum.Sums[] expected = new RowColSum.Sums[2];
        expected[0] = new RowColSum.Sums(3, 4);
        expected[1] = new RowColSum.Sums(7, 6);

        RowColSum.Sums[] methodSumReturned = RowColSum.sum(matrix);
        RowColSum.Sums[] methodAsyncSumReturned = RowColSum.asyncSum(matrix);

        assertThat(methodSumReturned, is(expected));
        assertThat(methodAsyncSumReturned, is(expected));
    }

    /**
     * Test for matrix 3x3.
     * @throws ExecutionException possible exceptions.
     * @throws InterruptedException possible exceptions.
     */
    @Test
    public void whenMatrixSizeIsThree() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RowColSum.Sums[] expected = new RowColSum.Sums[3];
        expected[0] = new RowColSum.Sums(6, 12);
        expected[1] = new RowColSum.Sums(15, 15);
        expected[2] = new RowColSum.Sums(24, 18);

        RowColSum.Sums[] methodSumReturned = RowColSum.sum(matrix);
        RowColSum.Sums[] methodAsyncSumReturned = RowColSum.asyncSum(matrix);

        assertThat(methodSumReturned, is(expected));
        assertThat(methodAsyncSumReturned, is(expected));
    }
}

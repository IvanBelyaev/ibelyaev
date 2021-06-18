package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * RowColSum.
 */
public class RowColSum {
    /**
     * Sums.
     */
    public static class Sums {
        /** Row sum. */
        private int rowSum;
        /** Column sum. */
        private int colSum;

        /**
         * Constructor.
         * @param rowSum row sum.
         * @param colSum column sum.
         */
        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        /**
         * Gets row sum.
         * @return row sum.
         */
        public int getRowSum() {
            return rowSum;
        }

        /**
         * Gets column sum.
         * @return column sum.
         */
        public int getColSum() {
            return colSum;
        }

        /**
         * The method checks objects for equality.
         * @param o the object with which to compare this object.
         * @return true if the objects are the same otherwise returns false.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum
                    && colSum == sums.colSum;
        }

        /**
         * The method returns a hash code for this object.
         * @return returns a hash code for this object.
         */
        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        /**
         * Method returns a string representing the object.
         * @return returns a string representing the object.
         */
        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    /**
     * Returns an array containing the sums of row elements and column elements.
     * The method is executed in a single thread.
     * @param matrix matrix.
     * @return an array containing the sums of row elements and column elements.
     */
    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < matrix.length; j++) {
                colSum += matrix[j][i];
                rowSum += matrix[i][j];
            }
            result[i] = new Sums(rowSum, colSum);
        }
        return result;
    }

    /**
     * Returns an array containing the sums of row elements and column elements.
     * The method is multithreaded.
     * @param matrix matrix.
     * @return an array containing the sums of row elements and column elements.
     * @throws ExecutionException possible exceptions.
     * @throws InterruptedException possible exceptions.
     */
    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        CompletableFuture<Sums>[] futures = new CompletableFuture[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            futures[i] = calculateAsyncSumColAndRow(matrix, i);
        }
        return Arrays.stream(futures).map(future -> future.join()).toArray(size -> new Sums[size]);
    }

    /**
     * Calculates the amounts for a specific column and row.
     * @param matrix matrix.
     * @param colRowIndex index for column and row.
     * @return the amounts for a specific column and row.
     */
    private static CompletableFuture<Sums> calculateAsyncSumColAndRow(int[][] matrix, int colRowIndex) {
        return CompletableFuture.supplyAsync(() -> {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < matrix.length; j++) {
                colSum += matrix[j][colRowIndex];
                rowSum += matrix[colRowIndex][j];
            }
            return new Sums(rowSum, colSum);
        });
    }
}

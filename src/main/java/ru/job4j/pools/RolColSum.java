package ru.job4j.pools;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public Sums() {
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sum = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sum[i] = countSum(matrix, i);
                sum[j] = countSum(matrix, j);
            }

        }
        return sum;
    }

    private static Sums countSum(int[][] matrix, int idx) {
        int countRow = 0;
        int countCol = 0;
        for (int i = 0; i < matrix.length; i++) {
            countRow += matrix[idx][i];
            countCol += matrix[i][idx];
        }
        return new Sums(countRow, countCol);
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] futureSum = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            futureSum[key] = futures.get(key).get();
        }
        return futureSum;

    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int idx) {
        return CompletableFuture.supplyAsync(() -> countSum(matrix, idx));
    }

}
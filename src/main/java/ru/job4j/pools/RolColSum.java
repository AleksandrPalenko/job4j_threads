package ru.job4j.pools;


import java.util.Arrays;

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
                sum[i].setRowSum(countSum(matrix, i));
                sum[j].setColSum(countSum(matrix, j));
                sum = new Sums[i];
            }
        }
        return sum;
    }

    private static int countSum(int[][] matrix, int idx) {
        int str = 0;
        for (int i = 0; i < matrix[idx].length; i++) {
            str += matrix[idx][i];
        }
        return str;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] futureSum = new Sums[matrix.length];
        return futureSum;

    }

    public static void main(String[] args) {
        int[][] sums = {
                {1, 2, 3},
                {2, 3, 4},
                {5, 6, 7}};
        System.out.println(Arrays.toString(asyncSum(sums)));
    }
}
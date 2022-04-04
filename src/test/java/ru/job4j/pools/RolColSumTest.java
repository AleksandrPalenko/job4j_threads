package ru.job4j.pools;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ExecutionException;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RolColSumTest {

    @Ignore
    @Test
    public void whenSumCountTest() {
        int[][] matrix = {
                {1, 2, 3},
                {2, 3, 4},
                {5, 6, 7}};
        assertThat(RolColSum.sum(matrix)[2].getColSum(), is(14));
    }

    @Test
    public void whenAsyncSumTest() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {2, 3, 4},
                {5, 6, 7}};
        assertThat(RolColSum.asyncSum(matrix).length, is(3));
    }

}
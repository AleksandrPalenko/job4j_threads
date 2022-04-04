package ru.job4j.pools;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RolColSumTest {

    @Ignore
    @Test
    public void whenSumCountTest() {
        int[][] sum = {
                {1, 2, 3},
                {2, 3, 4},
                {5, 6, 7}};
        int[][] expected = {
                {6, 8},
                {9, 11},
                {18, 14}};
        RolColSum.Sums[] sums = RolColSum.sum(sum);
        assertThat(sums, is(expected));
    }
}
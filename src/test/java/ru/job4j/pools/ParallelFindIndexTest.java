package ru.job4j.pools;

import junit.framework.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ParallelFindIndexTest {

    @Test
    public void whenFinderIsTrue() {
        String[] obj = {"Petr", "Ivan", "Olga"};
        String el = "Olga";
        int expected = 2;
        int rsl = ParallelFindIndex.findIndex(obj, el);
        Assert.assertEquals(expected, rsl);
    }

    @Test
    public void whenFinderIsMoreObjectsAndString() {
        String[] str = new String[100];
        for (int i = 0; i < str.length; i++) {
            str[i] = i + "a";
        }
        String el = "Olga";
        int expected = -1;
        int rsl = ParallelFindIndex.findIndex(str, el);
        Assert.assertEquals(expected, rsl);
    }

    @Test
    public void whenFinderIsMoreObjectsAndInt() {
        Integer[] obj = new Integer[100];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = i + 1;
        }
        int rsl = ParallelFindIndex.findIndex(obj, 50);
        assertThat(rsl, is(49));
    }

}
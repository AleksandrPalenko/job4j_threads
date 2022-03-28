package ru.job4j.cache;

import static org.hamcrest.Matchers.is;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CacheTest {

    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        Base base1 = new Base(2, 2);
        assertTrue(cache.add(base));
        assertTrue(cache.add(base1));
    }

    @Test
    public void whenUpdate() {
        Cache cache =  new Cache();
        Base base =  new Base(1, 1);
        Base base1 = new Base(1, 1);
        cache.add(base);
        cache.add(base1);
        assertTrue(cache.update(base1));
    }

    @Test
    public void whenUpdateFalse() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        Base newBase = new Base(2, 0);
        cache.add(base);
        assertFalse(cache.update(newBase));
    }

    @Test
    public void whenUpdateIsTrue() {
        Cache cache = new Cache();
        Base base = new Base(1, 2);
        Base newBase = new Base(1, 2);
        base.setName("Base");
        base.setName("NewBase");
        assertTrue(cache.add(base));
        assertTrue(cache.update(newBase));

    }

    @Test
    public void whenRemove() {
        Cache cache =  new Cache();
        Base base =  new Base(1, 1);
        Base base1 = new Base(1, 1);
        cache.add(base);
        cache.add(base1);
        cache.delete(base1);
        assertThat(cache.getSize(), is(1));
    }
}
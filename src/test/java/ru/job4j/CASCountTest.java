package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void countTest() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(
        () -> {
            for (int i = 0; i < 5; i++) {
                count.increment();
            }
       });
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        count.increment();
                    }
                });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(10));
    }

}
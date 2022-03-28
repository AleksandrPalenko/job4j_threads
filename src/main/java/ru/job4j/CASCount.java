package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer val;
        do {
            val = count.get();
            if (val == null) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
        } while (count.compareAndSet(val, val + 1));
    }

    public int get() {
        throw new UnsupportedOperationException("Count is not impl.");
    }
}
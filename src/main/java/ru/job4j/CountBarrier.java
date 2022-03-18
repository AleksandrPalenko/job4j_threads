package ru.job4j;

import net.jcip.annotations.GuardedBy;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;
    @GuardedBy("monitor")
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (this.count < this.total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
package ru.job4j.pool;

import ru.job4j.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool implements Runnable {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(8);

    public void work(Runnable job) throws InterruptedException {
        new Thread(
                () -> {
            while (!tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    for (int i = 0; i < threads.size(); i++) {
                        tasks.offer(job);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized void shutdown() {
        tasks.notifyAll();
    }

    @Override
    public void run() {
        while (!tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
            try {
                threads.add((Thread) tasks.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getSize() {
        return Runtime.getRuntime().availableProcessors();
    }
}
package ru.job4j.pool;

import ru.job4j.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int limit = Runtime.getRuntime().availableProcessors();
        threads.add(new Thread(
                () -> {
                    /*проверяем, что очередь не пустая или нить не выключили*/
                    while (!tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            for (int i = 0; i < limit; i++) {
                                tasks.poll().run();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }));
    }

    public void work(Runnable job) throws InterruptedException {
        while (!tasks.isEmpty()) {
            tasks.offer(job);
        }
    }

    public synchronized void shutdown() {
        new Thread(
                () -> {
                    for (Thread thread:threads) {
                        thread.interrupt();
                    }
                }
        );
    }

    public static void main(String[] args) throws InterruptedException {
       /*
        ThreadPool threadPool =  new ThreadPool();
        int limit = Runtime.getRuntime().availableProcessors();
        for (Runnable i; i < limit;) {
            threadPool.work(i);
        }
        Thread.sleep(2000);
        threadPool.shutdown();
        System.out.println(threadPool);

        */
    }
}
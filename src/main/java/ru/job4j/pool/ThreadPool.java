package ru.job4j.pool;

import ru.job4j.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int limit = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i <= limit; i++) {
            Thread progress = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    });
            progress.start();
            threads.add(progress);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public synchronized void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        Runnable run = () -> System.out.println(Thread.currentThread().getName());
        for (int i = 0; i <= 8; i++) {
            threadPool.work(run);
            System.out.println(i);
        }
        Thread.sleep(2000);
        threadPool.shutdown();
    }
}
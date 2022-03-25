package ru.job4j.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(7);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(s -> {
                        try {
                            queue.offer(s);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        Thread anotherProducer = new Thread(
                () -> {
                    IntStream.range(5, 10).forEach(s -> {
                        try {
                            queue.offer(s);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        anotherProducer.start();
        consumer.start();
        producer.join();
        anotherProducer.interrupt();
        anotherProducer.join();
        consumer.interrupt();
        consumer.join();
        Assert.assertThat(10, is(buffer.size()));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(s -> {
                        try {
                            queue.offer(s);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        Assert.assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}
package ru.job4j.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(7);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(s -> {
                        try {
                            queue.offer(s);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        queue.poll();
                        queue.poll();
                        queue.poll();
                        queue.poll();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        Assert.assertThat(4, is(queue.poll()));
    }

    @Test
    public void whenTwoConsumersAreWorking() throws InterruptedException {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(8);
        Thread producer = new Thread(() -> {
                    for (int i = 0; i < 8; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
        Thread consumer =  new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        try {
                            queue.poll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
        Thread anotherConsumer =  new Thread(() -> {
                    for (int i = 0; i < 3; i++) {
                        try {
                            queue.poll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
        producer.start();
        consumer.start();
        anotherConsumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        anotherConsumer.interrupt();
        anotherConsumer.join();
        assertThat(queue.poll(), is(6));
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
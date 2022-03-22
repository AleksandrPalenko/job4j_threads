package ru.job4j.queue;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;


public class SimpleBlockingQueueTest {

    @Ignore
    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        final Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        final Thread consumer = new Thread(
                () -> {
                    try {
                        queue.poll();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        producer.start();
        consumer.start();
        consumer.interrupt();
        consumer.join();
        producer.interrupt();
        producer.join();
        Assert.assertThat(queue.poll(), equalTo(5));
    }
}
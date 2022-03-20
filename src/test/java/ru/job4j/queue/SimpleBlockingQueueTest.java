package ru.job4j.queue;

import org.junit.Ignore;
import org.junit.Test;


public class SimpleBlockingQueueTest {

    @Ignore
    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(10);
        final Thread producer = new Thread(
                () -> {
                    queue.offer(5);
                }
        );
        final Thread consumer = new Thread(
                queue::poll
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();

    }
}
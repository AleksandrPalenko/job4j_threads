package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    /*
     * @param Producer помещает данные в очередь
     * @param Consumer извлекает данные из очереди
     */

    private int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue() {
        this.limit = Integer.MAX_VALUE;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (this.queue.size() > this.limit) {
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    /*
     * @return вернуть объект из внутренней коллекции
     * Если в коллекции объектов нет, то нужно перевести текущую нить в состояние ожидания
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T type = queue.poll();
        notifyAll();
        return type;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

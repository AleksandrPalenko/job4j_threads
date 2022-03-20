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

    //private final SimpleBlockingQueue<Integer> monitor = new SimpleBlockingQueue<>();
    @GuardedBy("monitor")
    private final int limit;
    private final Object monitor = this;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public void offer(T value) {
        synchronized (monitor) {
            if (this.queue.size() > this.limit) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            queue.add(value);
            monitor.notifyAll();
        }
    }

    /*
     * @return вернуть объект из внутренней коллекции
     * Если в коллекции объектов нет, то нужно перевести текущую нить в состояние ожидания
     */
    public T poll() {
        synchronized (monitor) {
            if (queue.isEmpty()) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        T type = queue.poll();
        monitor.notifyAll();
        return type;
    }
}

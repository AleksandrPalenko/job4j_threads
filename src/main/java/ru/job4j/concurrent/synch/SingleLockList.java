package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public T get(int index) {
        return list.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    public synchronized List<T> copy(List<T> newList) {
        newList.addAll(list);
        return newList;
    }

}
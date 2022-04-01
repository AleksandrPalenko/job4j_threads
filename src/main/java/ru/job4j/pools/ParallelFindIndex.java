package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T obj;
    private final int from;
    private final int to;

    public ParallelFindIndex(T[] array, T obj, int from, int to) {
        this.array = array;
        this.obj = obj;
        this.from = from;
        this.to = to;
    }

    public static <T> int find(T[] array, T obj, int from, int to) {
        int idx = -1;
        for (int i = from; i <= to; i++) {
            if (obj.equals(array[i])) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    @Override
    protected Integer compute() {
        if ((from - to) <= 10) {
            return find(array, obj, from, to);
        }
        int mid = (from + to) / 2;
        ParallelFindIndex<T> leftFind = new ParallelFindIndex<>(array, obj, from, to);
        ParallelFindIndex<T> rightFind = new ParallelFindIndex<>(array, obj, mid + 1, to);
        leftFind.fork();
        rightFind.fork();
        Integer left = (Integer) leftFind.join();
        Integer right = (Integer) rightFind.join();
        return Math.max(left, right);
    }

    public static <T> int findIndex(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFindIndex<>(array, value, 0, array.length - 1));
    }

}

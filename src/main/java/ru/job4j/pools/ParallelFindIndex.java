package ru.job4j.pools;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex extends RecursiveTask<Integer> {

    private final String[] array;
    private final String obj;
    private final int from;
    private final int to;

    public ParallelFindIndex(String[] array, String obj, int from, int to) {
        this.array = array;
        this.obj = obj;
        this.from = from;
        this.to = to;
    }

    public static int find(String[] object, String obj, int from, int to) {
        int idx = -1;
        for (int i = from; from <= to; i++) {
            if (obj.equals(object[i])) {
                idx = i;
            }
        }
        return idx;
    }

    @Override
    protected Integer compute() {
        int rsl = 0;
        if (array.length <= 10) {
            return find(array, obj, from, to);
        }
        int mid = (from + to) / 2;
        ParallelFindIndex leftFind = new ParallelFindIndex(array, obj, from, to);
        ParallelFindIndex rightFind = new ParallelFindIndex(array, obj, mid + 1, to);
        leftFind.fork();
        rightFind.fork();
        Integer left = leftFind.join();
        Integer right = rightFind.join();
        return rsl;
    }

    public static int findIndex(String[] object, String val) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFindIndex(object, val, 0, object.length - 1));
    }

}

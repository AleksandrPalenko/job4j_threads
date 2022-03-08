package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread second = new Thread();
        Thread first = new Thread(
                () -> {
                    while (second.getState() != Thread.State.TERMINATED) {
                        System.out.println(second.getState());
                    }
                }
        );
        second.start();
        System.out.println(first.getState());
        first.start();
        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
        }
        System.out.println(first.getState());
    }
}
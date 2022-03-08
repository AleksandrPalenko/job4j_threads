package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    Thread second = new Thread();
                    while (second.getState() != Thread.State.TERMINATED) {
                        System.out.println(second.getState());
                    }
                }
        );
        System.out.println(first.getState());
        first.start();
        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
        }
        System.out.println(first.getState());
    }
}
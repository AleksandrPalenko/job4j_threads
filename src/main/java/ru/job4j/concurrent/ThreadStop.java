package ru.job4j.concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("start ...");
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                            Thread.currentThread().interrupt(); /*прерываение*/
                           /* если используются методы sleep(),join() или wait(), то нужно в блоке catch вызвать прерывание.*/
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
        progress.join();
    }
}
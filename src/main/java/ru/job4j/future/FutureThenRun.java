package ru.job4j.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static ru.job4j.future.FutureRunAsync.goToTrash;
import static ru.job4j.future.FutureRunAsync.iWork;

public class FutureThenRun {

    public static void thenRunExample() throws Exception {
        CompletableFuture<Void> gtt = goToTrash();
        gtt.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                System.out.println("Сын: я мою руки");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
            System.out.println("Сын: Я помыл руки");
        });
        iWork();
    }

    public static void main(String[] args) throws Exception {
        thenRunExample();
    }
}

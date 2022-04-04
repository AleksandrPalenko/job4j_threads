package ru.job4j.future;

import java.util.concurrent.CompletableFuture;

import static ru.job4j.future.FutureRunAsync.iWork;
import static ru.job4j.future.FutureSupplyAsync.buyProduct;

public class FutureThenAccept {

    public static void thenAcceptExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко");
        bm.thenAccept((product) -> System.out.println("Сын: Я убрал " + product + " в холодильник "));
        iWork();
        System.out.println("Куплено: " + bm.get());
    }

    public static void main(String[] args) throws Exception {
        thenAcceptExample();
    }
}

package ru.job4j.future;

import java.util.concurrent.CompletableFuture;

import static ru.job4j.future.FutureRunAsync.goToTrash;
import static ru.job4j.future.FutureRunAsync.iWork;
import static ru.job4j.future.FutureSupplyAsync.buyProduct;

public class FutureThenCompose {

    public static void thenComposeExample() throws Exception {
        CompletableFuture<Void> result = buyProduct("Молоко").thenCompose(a -> goToTrash());
        iWork();
    }

    public static void main(String[] args) throws Exception {
        thenComposeExample();
    }
}

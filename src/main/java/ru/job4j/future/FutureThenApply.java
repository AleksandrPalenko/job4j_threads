package ru.job4j.future;

import java.util.concurrent.CompletableFuture;

import static ru.job4j.future.FutureRunAsync.iWork;
import static ru.job4j.future.FutureSupplyAsync.buyProduct;

public class FutureThenApply {

    public static void thenApplyExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко")
                .thenApply((product) -> "Сын: я налил тебе в кружку " + product + ". Держи.");
        iWork();
        System.out.println(bm.get());
    }

    public static void main(String[] args) throws Exception {
        thenApplyExample();
    }
}

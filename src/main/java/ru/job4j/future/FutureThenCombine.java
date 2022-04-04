package ru.job4j.future;

import java.util.concurrent.CompletableFuture;

import static ru.job4j.future.FutureRunAsync.iWork;
import static ru.job4j.future.FutureSupplyAsync.buyProduct;

public class FutureThenCombine {

    public static void thenCombineExample() throws Exception {
        CompletableFuture<String> result = buyProduct("Молоко")
                .thenCombine(buyProduct("Хлеб"), (r1, r2) -> "Куплены " + r1 + " и " + r2);
        iWork();
        System.out.println(result.get());
    }

    public static void main(String[] args) throws Exception {
        thenCombineExample();
    }
}

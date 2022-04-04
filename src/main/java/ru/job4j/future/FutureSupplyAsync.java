package ru.job4j.future;

import ru.job4j.future.FutureRunAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FutureSupplyAsync {

    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Сын: Мам/Пам, я пошел в магазин");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сын: Мам/Пап, я купил " + product);
                    return product;
                }
        );
    }

    public static void supplyAsyncExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко");
        FutureRunAsync.iWork();
        System.out.println("Куплено: " + bm.get());
    }

    public static void main(String[] args) throws Exception {
        supplyAsyncExample();
    }
}

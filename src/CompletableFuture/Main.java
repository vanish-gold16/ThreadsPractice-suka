package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Main {

    static void main() throws ExecutionException, InterruptedException {
/**
        for returning statements
        final CompletableFuture<String> futureEasy = CompletableFuture.supplyAsync(() -> {

            return "future is done";
        });

        final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("52");
        });

        future.thenApply(result -> result * 2);
        future.thenAccept(result -> System.out.println(result));
        future.thenRun(() -> System.out.println("готово"));

        Если добавить Async, то следующий шаг выполнится в другом потоке:

        thenApplyAsync

        thenAcceptAsync

        thenRunAsync

        Короче, добавили Async — будущая таска выполняется в пуле, а не в текущем потоке.

        // when the result
        future.thenCompose(result -> CompletableFuture.supplyAsync(() -> result * 10));
**/
        final CompletableFuture<String> future = new  CompletableFuture<>();
        new Thread(() -> {
            System.out.println("Job started");
            try {
                sleep(TimeUnit.SECONDS.toMillis(3));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            future.complete("Future is done");

            System.out.println("Job finished");
        }).start();
        System.out.println("waiting...");

        String result = future.get();
        System.out.println("retult is - " + result);
    }

}

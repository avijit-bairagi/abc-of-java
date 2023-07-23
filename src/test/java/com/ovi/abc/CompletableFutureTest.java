package com.ovi.abc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompletableFutureTest {

    @Test
    void simpleCompletableFutureTest() {

        /**
         https://medium.com/javarevisited/java-completablefuture-c47ca8c885af
         */

        Supplier<String> task = () -> {
            SleepUtils.sleep(5);
            return "Hello from CompletableFuture";
        };

        CompletableFuture.supplyAsync(task)
                .thenAccept(s -> System.out.println("Result: " + s));

        CompletableFuture.supplyAsync(() -> 10)
                .thenApplyAsync(r -> r * 2)
                .thenApplyAsync(r -> r + 2)
                .thenAccept(r -> System.out.println("Result: " + r));
        SleepUtils.sleep(7);
    }

    @Test
    void acceptVsAcceptAsyncTest() {

        /**
         1. thenApply(fn) - runs fn on a thread defined by the CompletableFuture on which it is called,
         so you generally cannot know where this will be executed.
         It might immediately execute if the result is already available.
         2. thenApplyAsync(fn) - runs fn on a environment-defined executor regardless of circumstances.
         For CompletableFuture this will generally be ForkJoinPool.commonPool().
         3. thenApplyAsync(fn,exec) - runs fn on exec.
         In the end the result is the same, but the scheduling behavior depends on the choice of method.
         */

        //accept
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 10;
        }).thenApply(r -> {
            System.out.println(Thread.currentThread().getName());
            return r * 2;
        }).thenApply(r -> {
            System.out.println(Thread.currentThread().getName());
            return r + 5;
        }).thenAccept(r -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Result: " + r);
        });

        //accept async
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 10;
        }).thenApplyAsync(r -> {
            System.out.println(Thread.currentThread().getName());
            return r * 2;
        }).thenApplyAsync(r -> {
            System.out.println(Thread.currentThread().getName());
            return r + 5;
        }).thenAcceptAsync(r -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Result: " + r);
        });

        //accept async with executor service
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 10;
                }, executorService)
                .thenApplyAsync(r -> {
                    System.out.println(Thread.currentThread().getName());
                    return r * 2;
                }, executorService)
                .thenApply(r -> {
                    System.out.println(Thread.currentThread().getName());
                    return r + 5;
                })
                .thenAcceptAsync(r -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("Result: " + r);
                }, executorService);
    }

    @Test
    void acceptVsCombineVsComposeTest() {

        /**
         https://medium.com/@joshikeyur/apply-compose-and-combine-futures-71b76b3a1aae

         1. thenCombine: This method is used when both processes (CompletableFuture) can work independently
         and once both are done their results can be combined to get the final result.

         2. thenCompose: This method is used when one CompletableFuture is waiting for another CompletableFuture
         to provide its result. Once it is available result will be processed asynchronously.

         3. thenApply: This method is more or less same and thenCompose only difference
         is it will return the result instead of another CompletableFuture in its Lambda.
         */

        //accept
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> {
                    System.out.println(s);
                    return s.length();
                }).thenAccept(System.out::println);

        //combine
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "Task 1");

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "Task 2");

        CompletableFuture<String> result = task1.thenCombine(task2, (r1, r2) -> r1 + ", " + r2 + " are done.");

        result.thenAccept(System.out::println);


        //compose
        CompletableFuture<List<Integer>> intList = CompletableFuture.supplyAsync(() -> Arrays.asList(1, 2, 3, 4, 5));

        intList.thenComposeAsync(list -> CompletableFuture.supplyAsync(() -> list.stream().map(s -> s * s)
                .collect(Collectors.toList()))).thenAccept(integers -> {
            integers.forEach(System.out::println);
        });

    }
}

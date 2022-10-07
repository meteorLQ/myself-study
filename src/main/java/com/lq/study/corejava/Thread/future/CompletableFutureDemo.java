package com.lq.study.corejava.Thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步回调
 *
 * @author LQ
 * @date 2020/08/03 9:34
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //没有返回值
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "Void");
//        });
//        System.out.println("main-----");
//        completableFuture.get();

        //有返回值的回调
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() ->
        {
            int i = 1 / 0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t==>>" + t);
            System.out.println("u==>>" + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 500;
        }).get());
    }
}

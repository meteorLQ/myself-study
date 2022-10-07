package com.lq.study.corejava.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author LQ
 * @date 2020/08/03 0:23
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //  test1();
        //test2();
        test3();
    }


    public static void test1() {
        Long sum = 0l;
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 10_000000l; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "   耗时 " + (end - start));

    }

    public static void test2() throws ExecutionException, InterruptedException {
        Long sum = 0l;
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask task = new ForkJoin(0l, 10_00000000l);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "   耗时 " + (end - start));

    }

    public static void test3() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0l, 10_00000000l).parallel().reduce(0, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "   耗时 " + (end - start));

    }
}

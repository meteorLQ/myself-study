package com.lq.study.corejava.Thread.线程池;

import java.util.concurrent.*;

/**
 * 线程池的创建
 * 模拟银行办理业务
 * 2个常用柜台，3个不常用柜台 ，一共5个柜台
 *
 * @author LQ
 * @date 2020/03/20 23:57
 */
public class MyThreadPool {


    public static void main(String[] args) {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {

                System.out.println(Thread.currentThread().getName() + "办理业务");
                return Thread.currentThread().getName();
            }
        });
        //一池5个处理线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池一个线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池多个线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();

        //手动创建线程
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                // 核心线程数
                2,
                // 最大线程数
                5,
                // 当线程数大于核心线程数，多余的线程没用被调用存活时间
                2L,
                // 时间单位
                TimeUnit.SECONDS,
                // 任务队列，用来储存等待执行任务的队列
                new LinkedBlockingQueue<Runnable>(3),
                // 线程工厂
                Executors.defaultThreadFactory(),
                // Executors默认的拒绝策略,抛出异常
//                new ThreadPoolExecutor.AbortPolicy()
                // 将任务回退到调用者
//                new ThreadPoolExecutor.CallerRunsPolicy()
                // 此策略将丢弃最早的未处理的任务请求。
//                new ThreadPoolExecutor.DiscardOldestPolicy()
                //  不处理新任务，直接丢弃掉。
                new ThreadPoolExecutor.DiscardPolicy()
        );
        try {
            for (int i = 0; i < 10; i++) {
//                Future<String> future = (Future<String>) threadPool.submit(futureTask);
//                System.out.println(future.get());
                int finalI = i;
                threadPool.execute(() -> {
//                    if (finalI == 1) {
//                        try {
//                            TimeUnit.SECONDS.sleep(5);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池不再接受任何新任务,但是队列中的任务会执行完毕
            threadPool.shutdown();
            //立刻停止正在执行的任务，并停止处理队列中等待执行的任务,返回队列中等待的任务集合
            //threadPool.shutdownNow();
        }


    }
}

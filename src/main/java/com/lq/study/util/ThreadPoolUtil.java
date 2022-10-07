package com.lq.study.util;

import java.util.concurrent.*;

/**
 * 线程池工具类
 *
 * @author LQ
 * @date 2020/08/01 18:49
 */
public class ThreadPoolUtil {
    public static ThreadPoolExecutor initPool() {
        // 一池5个处理线程
//          ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池一个线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();//一池多个线程

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                10,
                2L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
//                new ThreadPoolExecutor.DiscardPolicy()
        );
        return threadPool;
    }


    /**
     * 停止线程池
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍人超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {

                    }
                }
            } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}

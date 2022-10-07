package com.lq.study.corejava.Thread.并发包中一些特殊的类;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * <p>
 * 典型应用限流
 *
 * @author LQ
 * @date 2020/08/02 0:04
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();
        Semaphore semaphore = new Semaphore(3);

        try {
            for (int i = 1; i <= 5; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " 抢到车位");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(Thread.currentThread().getName() + "离开车位");
                        semaphore.release();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            }
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }
    }
}

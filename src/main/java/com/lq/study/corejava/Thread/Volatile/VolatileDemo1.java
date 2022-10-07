package com.lq.study.corejava.Thread.Volatile;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * volatile保证可见性
 *
 * @author LQ
 * @date 2020/08/03 11:31
 */
public class VolatileDemo1 {

    private volatile static int num;

    public static void main(String[] args) {
        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();

        try {
            for (int i = 0; i < 5; i++) {

                threadPoolExecutor.execute(() -> {
                    while (num == 0) {
                       // System.out.println(Thread.currentThread().getName());
                    }
                });
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"==="+num);
            num = 1;
            System.out.println("num=1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }

    }
}

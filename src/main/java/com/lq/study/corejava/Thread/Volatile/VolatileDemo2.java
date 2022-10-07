package com.lq.study.corejava.Thread.Volatile;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * volatile无法保证原子性
 *
 * @author LQ
 * @date 2020/08/03 11:31
 */
public class VolatileDemo2 {

    private  static int num = 0;

    public  static void add() {
        num++;
    }

    public static void main(String[] args) {
        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();

        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "add");
                    for (int j = 0; j < 1000; j++) {
                        add();
                    }
                });
                // threadPoolExecutor.shutdown();
            }
            TimeUnit.SECONDS.sleep(1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
            System.out.println(Thread.activeCount());
            while (Thread.activeCount() > 2) {
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "==>>" + num);
        }

    }
}

package com.lq.study.corejava.Thread.Volatile;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类保证原子性使用CAS
 *
 * @author LQ
 * @date 2020/08/03 11:31
 */
public class VolatileDemo3 {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public synchronized static Integer add() {
        return atomicInteger.getAndIncrement();
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
//            TimeUnit.SECONDS.sleep(1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
            System.out.println(Thread.activeCount());
            while (Thread.activeCount() > 2) {
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "==>>" + atomicInteger.get());
        }

    }
}

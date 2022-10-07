package com.lq.study.corejava.Thread.并发包中一些特殊的类;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * CountDownLatch 减法计数器
 * 一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
 *
 * @author LQ
 * @date 2020/08/01 23:25
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();

        try {
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    //计数器减1
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + "==>" + finalI + "号组件加载完毕");

                });
            }


            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + "服务启动成功！");


        } catch (InterruptedException e) {


        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }


    }
}

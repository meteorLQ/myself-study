package com.lq.study.corejava.Thread.并发包中一些特殊的类;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 减法计数器
 * 实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，
 * 然后同时开跑。做法是初始化一个共享的 CountDownLatch 对象，将其计数器初始化为 1 ：new CountDownLatch(1)，
 * 多个线程在开始执行任务前首先 coundownlatch.await()，当主线程调用 countDown() 时，计数器变为 0，多个线程同时被唤醒。
 *
 * @author LQ
 * @date 2020/08/01 23:25
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();

        try {
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "==>" + finalI + "到达终点");
                });
            }
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + "发令枪开枪-----“砰。。。。。”");
            countDownLatch.countDown();

        } catch (Exception e) {
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }


    }
}

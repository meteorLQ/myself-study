package com.lq.study.corejava.Thread.并发包中一些特殊的类;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * CyclicBarrier
 * 允许一组线程全部等待彼此达到共同屏障点的同步辅助。
 * 循环阻塞在涉及固定大小的线程方的程序中很有用，这些线程必须偶尔等待彼此。 屏障被称为循环 ，因为它可以在等待的线程被释放之后重新使用。
 * <p>
 * <p>
 * 拼团
 *
 * @author LQ
 * @date 2020/08/01 23:37
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("龙珠已集齐，神龙召唤！");
        });

        try {
            for (int i = 1; i <= 5; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 收集第 " + finalI + "龙珠");
                        cyclicBarrier.await();
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

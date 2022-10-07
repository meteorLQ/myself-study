package com.lq.study.corejava.Thread.Queue;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 同步阻塞队列
 * SynchronousQueuem没有容量它本身不存储元素，每一个put操作，就必须等待一个take操作，否则必须阻塞等待
 *
 * @author LQ
 * @date 2020/08/02 14:08
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();

        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();

        try {
            for (int i = 0; i < 5; i++) {
                threadPoolExecutor.execute(() -> {
                    try {
                        // TimeUnit.SECONDS.sleep(3);
                        synchronousQueue.put(1);
                        System.out.println(Thread.currentThread().getName() + "==>put 1");
//                        System.out.println(Thread.currentThread().getName() + "==>put 1");
//                        synchronousQueue.put(1);
//                        System.out.println(Thread.currentThread().getName() + "==>put 1");
//                        synchronousQueue.put(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            for (int i = 0; i < 5; i++) {
                threadPoolExecutor.execute(() -> {
                    try {
//                        TimeUnit.SECONDS.sleep(1);
                        synchronousQueue.take();
                        System.out.println(Thread.currentThread().getName() + "task 1");
//                        TimeUnit.SECONDS.sleep(3);
//                        synchronousQueue.take();
//                        System.out.println(Thread.currentThread().getName() + "task 1");
//                        TimeUnit.SECONDS.sleep(3);
//                        synchronousQueue.take();
//                        System.out.println(Thread.currentThread().getName() + "task 1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }
    }
}

package com.lq.study.corejava.Thread.Lock;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者
 * 使用while解决虚假唤醒问题
 *
 * @author LQ
 * @date 2020/03/22 17:22
 */
public class LockAndCondition {
    private int num = 0;

    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //生产者
    private void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    //消费者
    private void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockAndCondition threadWaitNotifyDemo = new LockAndCondition();
        ThreadPoolExecutor pool =
                new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS, new SynchronousQueue<>()
                );
        try {
            pool.execute(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        threadWaitNotifyDemo.increment();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            pool.execute(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        threadWaitNotifyDemo.increment();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            pool.execute(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        threadWaitNotifyDemo.decrement();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            pool.execute(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        threadWaitNotifyDemo.decrement();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            pool.shutdown();
        }


    }
}

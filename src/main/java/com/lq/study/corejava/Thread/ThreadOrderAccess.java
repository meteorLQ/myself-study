package com.lq.study.corejava.Thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程1打印5次
 * 线程2打印10次
 * 线程3打印15次
 *
 * @author LQ
 * @date 2020/03/22 21:13
 */
class ShareSource {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 2;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print10() {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 1;
               condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


public class ThreadOrderAccess {
    public static void main(String[] args) {
     ShareSource shareSource = new ShareSource();
        ThreadPoolExecutor pool =
                new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS, new SynchronousQueue<>()
                );
        try {

            pool.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    shareSource.print5();
                }

            });

            pool.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    shareSource.print10();
                }

            });

            pool.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    shareSource.print15();
                }
            });
        } finally {
            pool.shutdown();
        }
    }
}

package com.lq.study.corejava.Thread.Lock;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition可以精准唤醒
 * <p>
 * 打印ABCD
 *
 * @author LQ
 * @date 2020/08/01 17:48
 */
public class ConditionTest {
    private Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    Condition conditionD = lock.newCondition();

    /**
     * flag =0 打印A
     * flag =1 打印B
     * flag =2 打印C
     * flag =3 打印D
     */
    int flag = 0;

    private void printA() {
        lock.lock();
        try {
            while (flag != 0) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->" + "A");
            flag = 1;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void printB() {
        lock.lock();
        try {
            while (flag != 1) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->" + "B");
            flag = 2;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void printC() {
        lock.lock();
        try {
            while (flag != 2) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->" + "C");
            flag = 3;
            conditionD.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void printD() {
        lock.lock();
        try {
            while (flag != 3) {
                conditionD.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->" + "D");
            flag = 0;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest threadWaitNotifyDemo = new ConditionTest();
        ThreadPoolExecutor pool =
                new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS, new SynchronousQueue<>()
                );
        try {
            pool.execute(() -> {

                for (int i = 0; i < 10; i++) {
                    threadWaitNotifyDemo.printA();
                }

            });

            pool.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    threadWaitNotifyDemo.printB();
                }
            });

            pool.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    threadWaitNotifyDemo.printC();
                }
            });

            pool.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    threadWaitNotifyDemo.printD();
                }
            });
        } finally {
            pool.shutdown();
        }


    }
}

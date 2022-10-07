package com.lq.study.corejava.Thread.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题1:三个线程打印十遍ABC
 * 题2:  打印十遍AAABBBBBCCCCCCCCCC
 * Condition精确控制唤醒
 *
 * @author LQ
 * @date 2019/04/14 17:19
 */
public class TestABCAlternate {
    public static void main(String[] args) {
        AlternateDemo alternateDemo = new AlternateDemo();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternateDemo.loopA(i);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternateDemo.loopB(i);
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternateDemo.loopC(i);
            }
        }, "C").start();

    }

}

class AlternateDemo {
    private int number = 1;//当前运行的线程标记

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void loopA(int tol) {
        lock.lock();
        try {
            if (number != 1) {
                conditionA.await();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + tol + "\t" + i);
            }
            number = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int tol) {
        lock.lock();
        try {
            if (number != 2) {
                conditionB.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + tol + "\t" + i);

            }
            number = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int tol) {
        lock.lock();
        try {
            if (number != 3) {
                conditionC.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + tol + "\t" + i);
            }
            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

package com.lq.study.corejava.Thread.JavaLock;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * Lcok 和 synchronized
 * 可重入锁
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，在同一个线程在外层方法获取锁的时候，
 * 在进入内层方法会自动获取锁
 * 也即是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 *
 * @author LQ
 * @date 2020/08/03 18:34
 */
public class JavaLockDemo01 {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();
        JavaLockDemo01 javaLockDemo01 = new JavaLockDemo01();
        try {
            threadPoolExecutor.execute(() -> {
                javaLockDemo01.run();
            });

            threadPoolExecutor.execute(() -> {
                javaLockDemo01.run();
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }


    }

    public synchronized void run() {
        System.out.println(Thread.currentThread().getName() + "runing...............");
        jump();
    }

    public synchronized void jump() {
        System.out.println(Thread.currentThread().getName() + "jumping...............");

    }
}

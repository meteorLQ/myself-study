package com.lq.study.coreJava.Thread.ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 每个线程f线程自己的内存变量threadLocals中，
 * 如果当前线程一直不消亡，那么这些本地变量会一直存在，所以可能会造成内存溢出，
 * 因此使用完毕后要记得调用ThreadLocal的remove方法删除对应线程的threadLocals中的本地变量。
 *
 * @author LQ
 * @date 2020/09/10 16:49
 */
public class ThreadLocalTest {
    static ThreadLocal<Integer> threadLocal = new ThreadLocal();
    public volatile static int num = 0;

    public synchronized static void add() {
        num++;
    }

    public static void main(String[] args) throws InterruptedException {
        // ThreadLocalTest threadLocalTest = new ThreadLocalTest();

        new Thread(() -> {
            threadLocal.set(1);
            System.out.println(Thread.currentThread().getName() + "===" + threadLocal.get());
        }).start();

        new Thread(() -> {
            threadLocal.set(2);
            System.out.println(Thread.currentThread().getName() + "===" + threadLocal.get());
        }).start();

    }
}

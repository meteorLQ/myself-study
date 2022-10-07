package com.lq.study.coreJava.Thread.ThreadLocal;

/**
 * InheritableThreadLocal子线程访问父线程变量
 *
 * @author LQ
 * @date 2020/09/15 9:38
 */
public class InheritableThreadLocalTest {
    static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    public volatile static int num = 0;

    public synchronized static void add() {
        num++;
    }

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(3);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "===" + threadLocal.get());
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "===" + threadLocal.get());
        }).start();

        System.out.println(Thread.currentThread().getName() + "===" + threadLocal.get());
    }
}

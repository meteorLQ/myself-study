package com.lq.study.coreJava.Thread.Synchronized;

import java.util.concurrent.TimeUnit;

/**
 * 一个线程当调用共享变量的wait方法时,如果另一个线程中断了该线程，会抛出InterruptedException
 *
 * @author LQ
 * @date 2020/09/02 14:34
 */
public class WaitNotifyInterupt {
    static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            try {
                System.out.println("synchronized start");
                synchronized (o) {
                    o.wait(0,2);
                }
                System.out.println("synchronized end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("interrupt start");
        thread.interrupt();
        System.out.println("interrupt end");
    }
}

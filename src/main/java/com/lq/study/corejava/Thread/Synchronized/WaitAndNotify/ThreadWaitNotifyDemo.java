package com.lq.study.corejava.Thread.Synchronized.WaitAndNotify;

/**
 * 生产者消费者synchronized
 * 使用while解决虚假唤醒问题
 *
 * @author LQ
 * @date 2020/03/22 17:22
 */
public class ThreadWaitNotifyDemo {
    private int num = 0;

    //生产者
    private synchronized void increment() {
        while (num != 0) {
            System.out.println(Thread.currentThread().getName() + "进入生产者if判断");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();

    }

    //消费者
    private synchronized void decrement() {
        while (num == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();

    }

    public static void main(String[] args) {
        ThreadWaitNotifyDemo threadWaitNotifyDemo = new ThreadWaitNotifyDemo();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadWaitNotifyDemo.increment();
            }
        }, "A").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadWaitNotifyDemo.decrement();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadWaitNotifyDemo.increment();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadWaitNotifyDemo.decrement();
            }
        }, "D").start();


    }
}

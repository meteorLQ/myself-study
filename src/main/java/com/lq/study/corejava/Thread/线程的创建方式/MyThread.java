package com.lq.study.corejava.Thread.线程的创建方式;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现线程的第三种方式
 *
 * @author LQ
 * @date 2020/03/20 22:40
 */
public class MyThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask1 = new FutureTask(new MyThread3());
        FutureTask<Integer> futureTask2 = new FutureTask(new MyThread3());
        Thread thread1 = new Thread(futureTask1, "AA");
        Thread thread2 = new Thread(futureTask2, "BB");
        thread1.start();
        thread2.start();
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
    }
}

/**
 * 实现Callable接口(此接口带有一个返回值)
 */
class MyThread3 implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return 1024;
    }


}

/**
 * 继承Thread类
 */
class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("继承Thread类");
    }

    public static void main(String[] args) {
        new Thread(new MyThread1(),"继承Thread类").start();
    }

}

/**
 * 实现Runnable接口
 */
class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable接口");
    }

    public static void main(String[] args) {
        new Thread(new MyThread2()).start();

    }
}



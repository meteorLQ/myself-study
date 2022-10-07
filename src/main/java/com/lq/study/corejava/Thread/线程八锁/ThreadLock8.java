package com.lq.study.corejava.Thread.线程八锁;

import java.util.concurrent.TimeUnit;

/**
 * 线程八锁
 * 1.标准打印  email msn
 * 2.sendEmail 方法暂停四秒  email  msn
 * 3.增加一个实例方法  hello email msn
 * 4.俩个实例对象  MSN  Email
 * 5.俩静态同步方法，同一实例对象 Email MSN
 * 6.俩静态同步方法，俩个实例  Email msn
 * 7.一个静态同步方法，一个实例同步方法  一个实例 msn email
 * 8.一个静态同步方法，一个实例同步方法  俩个实例  msn email
 *
 * @author LQ
 * @date 2020/03/22 22:10
 */
class Resource {

    public synchronized void sendMSN() {
        System.out.println(Thread.currentThread().getName() + ".................send MSN");
    }

    public synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "..................send Email");
    }


    public void hello() {
        synchronized (this) {
            System.out.println("hello ");
        }
    }

}

public class ThreadLock8 {
    public static void main(String[] args) throws InterruptedException {
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();
//        new Thread(() -> {
//            resource1.sendMSN();
//        }, "线程B").start();

        new Thread(() -> {
            resource1.sendEmail();
        }, "线程A").start();

        new Thread(() -> {
            resource2.hello();
        }, "线程C").start();

        //TimeUnit.SECONDS.sleep(3);


    }
}

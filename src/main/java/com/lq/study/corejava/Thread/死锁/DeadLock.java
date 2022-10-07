package com.lq.study.corejava.Thread.死锁;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * 死锁的产生必须具备以下四个条件
 * 互斥条件
 * 指线程对已经获取到的资源进行排它性使用，
 * 即该资源同时只由一个线程占用。如果此时还有其他线程请求获取该资源，则请求者只能等待，直至占有资源的线程释放该资源。
 * <p>
 * 请求并持有条件
 * 指一个线程已经持有了至少一个资源，但又提出了新的资源请求，而新资源已被其他线程占有，
 * 所以当前线程会被阻塞，但阻塞的同时并不释放自己已经获取的资源。
 * <p>
 * 不可剥夺条件
 * 指线程获取到的资源在自己使用完之前不能被其他线程抢占，只有在自己使用完毕后才由自己释放该资源。
 * <p>
 * 环路等待条件
 * 指在发生死锁时，必然存在一个线程—资源的环形链，即线程集合{T0, T1, T2, …, Tn}中
 * 的T0正在等待一个T1占用的资源，T1正在等待T2占用的资源，……Tn正在等待已被T0占用的资源
 *
 * @author LQ
 * @date 2020/03/21 20:41
 */
public class DeadLock {

    static String resource1 = "resource1";
    static String resource2 = "resource2";

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + "get resource1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + "get resource2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + "get resource1");
                }
            }
        }, "线程 2").start();
    }

//    private String lockA;
//    private String lockB;
//
//    public DeadLock(String lockA, String lockB) {
//        this.lockA = lockA;
//        this.lockB = lockB;
//    }
//
//    @Override
//    public void run() {
//        synchronized (lockA) {
//            System.out.println(Thread.currentThread().getName() + "当前线程拿到锁" + lockA + "尝试获取" + lockB);
//            synchronized (lockB) {
//                System.out.println(Thread.currentThread().getName() + "当前线程拿到锁" + lockB + "尝试获取" + lockA);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        new Thread(new DeadLock("A", "B")).start();
//        new Thread(new DeadLock("A", "B")).start();
//    }
}

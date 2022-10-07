package com.lq.study.corejava.Thread.Lock;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个售票员  卖出    30张票
 * ReentrantLock方式实现
 *
 * @author LQ
 * @date 2020/03/22 14:55
 * <p>
 * 在高内聚低耦合的前提下，线程  操作  资源类
 */
class Ticket {
    private int number = 30;

    // 可重入锁
    Lock lock = new ReentrantLock();

    public void sale() {
        try {
            lock.lock();
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "剩余" + number);
            } else {
                System.out.println(Thread.currentThread().getName() + "卖完啦");

            }
        } finally {
            lock.unlock();
        }

    }
}

public class SaleTicketDemoAndReentrantLock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        ThreadPoolExecutor pool = new ThreadPoolExecutor
                (2, 5, 1L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        try {

            pool.execute(new Thread(() -> {
                for (int i = 0; i < 40; i++) {

                    ticket.sale();
                }
            }, "A"));

            pool.execute(new Thread(() -> {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }, "B"));

            pool.execute(new Thread(() -> {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }, "C"));

        } finally {
            pool.shutdown();
        }


    }
}

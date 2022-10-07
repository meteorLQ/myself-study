package com.lq.study.corejava.Thread.Synchronized;

/**
 * 三个售票员  卖出    30张票
 * Synchronized方式实现
 *
 * @author LQ
 * @date 2020/03/22 14:55
 * <p>
 * 在高内聚低耦合的前提下，线程  操作  资源类
 */
class Ticket {
    private int number = 30;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "剩余" + number);
        } else {
            System.out.println(Thread.currentThread().getName() + "卖完啦");
        }
    }
}

public class SaleTicketDemoAndSynchronized {

    public static void main(String[] args) {
        //资源类
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();

    }
}

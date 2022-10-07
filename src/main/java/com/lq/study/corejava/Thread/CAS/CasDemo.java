package com.lq.study.corejava.Thread.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS compareAndSet比较并交换
 *
 * @author LQ
 * @date 2020/08/03 15:57
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(25);
        //expect  update
        boolean b = atomicInteger.compareAndSet(25, 1);
        System.out.println(b + "===" + atomicInteger.get());
        boolean c = atomicInteger.compareAndSet(25, 2);
        System.out.println(c + "===" + atomicInteger.get());
    }
}

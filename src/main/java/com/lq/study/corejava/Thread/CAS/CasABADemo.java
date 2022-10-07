package com.lq.study.corejava.Thread.CAS;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS会出现ABA问题
 * 解决办法:使用乐观锁的思想
 * 带版本号的原子操作
 *
 * @author LQ
 * @date 2020/08/03 15:57
 */
public class CasABADemo {
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(25);
//        //expect  update
//        boolean b = atomicInteger.compareAndSet(25, 1);
//        System.out.println(b + "===" + atomicInteger.get());
//        atomicInteger.compareAndSet(1, 25);
//
//        boolean c = atomicInteger.compareAndSet(25, 6666);
//        System.out.println(c + "===" + atomicInteger.get());

        //解决ABA问题(乐观锁)
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(25, 1);
        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();
        try {

            threadPoolExecutor.execute(() -> {
                int stamp = atomicStampedReference.getStamp();
                String name = Thread.currentThread().getName();
                System.out.println(name + "  A1======>" + stamp);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "  A1======>" + atomicStampedReference.compareAndSet(25, 30, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
                System.out.println(name + "  A1======>" + atomicStampedReference.getStamp());
                System.out.println(name + "  A2======>" + atomicStampedReference.compareAndSet(30, 25, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
                System.out.println(name + "  A2======>" + atomicStampedReference.getStamp());


            });

            threadPoolExecutor.execute(() -> {
                int stamp = atomicStampedReference.getStamp();
                String name = Thread.currentThread().getName();
                System.out.println(name + "  B1======>" + stamp);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "  B1======>" + atomicStampedReference.compareAndSet(25, 66666, stamp, atomicStampedReference.getStamp() + 1));
                System.out.println(name + "  B1======>" + atomicStampedReference.getStamp());
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }
    }
}

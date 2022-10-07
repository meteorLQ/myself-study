package com.lq.study.corejava.Thread.JavaLock;

import com.lq.study.util.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author LQ
 * @date 2020/08/03 18:34
 */
public class JavaLockDemo02 {

    private AtomicReference<Thread> atomicReference = new AtomicReference();

    //加锁
    public void myLock() {

        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "mylock");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    //
    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "myunlock...............");

    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();
        JavaLockDemo02 javaLockDemo02 = new JavaLockDemo02();
        try {
            threadPoolExecutor.execute(() -> {
                try {
                    javaLockDemo02.myLock();
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    javaLockDemo02.unLock();

                }
            });

            TimeUnit.SECONDS.sleep(1);

            threadPoolExecutor.execute(() -> {
                try {
                    javaLockDemo02.myLock();
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    javaLockDemo02.unLock();

                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }


    }

}

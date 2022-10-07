package com.lq.study.coreJava.Thread.Join;

/**
 * @author LQ
 * @date 2020/09/07 10:26
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
//                if (i == 2) {
//                    Thread.yield();
//                }
                System.out.println("thread1 run ing===" + i);
            }

        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("thread2 run ing===" + i);
            }

        });
        thread1.start();
        thread2.start();
        //thread2.join();
        //  thread1.join();
        System.out.println("main ing");
    }
}

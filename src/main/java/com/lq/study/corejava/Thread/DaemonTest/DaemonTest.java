package com.lq.study.coreJava.Thread.DaemonTest;

/**
 * 守护线程
 *
 * @author LQ
 * @date 2020/09/10 16:33
 */
public class DaemonTest {
    public static void main(String[] args) {
        new Thread(() -> {
            int i = 4;
            for (; i < 8;i++ ) {
                System.out.println("1");
            }
        }).start();
    }
}

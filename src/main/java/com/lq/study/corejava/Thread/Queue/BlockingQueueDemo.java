package com.lq.study.corejava.Thread.Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 *
 * @author LQ
 * @date 2020/08/02 13:48
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        test1();
    }

    /**
     * 如果队列为空或者队列已满抛出异常
     */
    public static void test1() {
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        // blockingQueue.add(4);
        System.out.println("=======================");
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }

    public static void test2() {
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        blockingQueue.add(4);
    }

    public static void test3() {
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        blockingQueue.add(4);
    }

    public static void test4() {
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        blockingQueue.add(4);
    }
}

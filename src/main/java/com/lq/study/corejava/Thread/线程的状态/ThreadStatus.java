package com.lq.study.corejava.Thread.线程的状态;

/**
 * 6种线程的状态
 *
 * @author LQ
 * @date 2020/08/01 12:06
 */
public class ThreadStatus {
    public static void main(String[] args) {
        // 新建
        Thread.State aNew = Thread.State.NEW;
        // 阻塞
        Thread.State blocked = Thread.State.BLOCKED;
        // 运行(包括就绪)
        Thread.State runnable = Thread.State.RUNNABLE;
        // 等待(死等，一直等)
        Thread.State waiting = Thread.State.WAITING;
        // 超时等待
        Thread.State timed_waiting = Thread.State.TIMED_WAITING;
        // 停止
        Thread.State terminated = Thread.State.TERMINATED;
    }
}

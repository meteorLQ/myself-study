package com.lq.study.corejava.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * forkjoin方式将任务拆分为小的任务
 * 求和计算任务
 *
 * @author LQ
 * @date 2020/08/02 22:52
 */
public class ForkJoin extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    static volatile int size = 1;


    private Long temp = 10000L;

    public ForkJoin(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0l;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }

            System.out.println("执行了" + size);
            size++;
            return sum;
        } else {
            //   中间值
            Long temp = (end + start) / 2;
            ForkJoin forkJoin1 = new ForkJoin(start, temp);
            forkJoin1.fork();
            ForkJoin forkJoin2 = new ForkJoin(temp + 1, end);
            forkJoin2.fork();
            return forkJoin1.join() + forkJoin2.join();
        }
    }
}

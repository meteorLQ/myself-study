package com.lq.study.corejava.Container.List.ArrayList;

import java.util.ArrayList;

/**
 * ArrayList性能测试
 *
 * @author LQ
 * @date 2020/07/30 18:11
 */
public class ArrayListPerformanceTest {
    public static void testArrayListA() {
        ArrayList<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();

        System.out.println("普通调用消耗时间~:" + (end - start));
    }

    public static void testArrayListB1() {
        ArrayList<Integer> list = new ArrayList<>();
        list.ensureCapacity(100000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();

        System.out.println("调用ensureCapacity消耗时间~:" + (end - start));
    }

    public static void testArrayListB2() {
        ArrayList<Integer> list = new ArrayList<>(100000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("创建时初始化100000消耗时间~:" + (end - start));
    }

    public static void main(String[] args) {
        testArrayListA();
        testArrayListB1();
        testArrayListB2();
    }
}

package com.lq.study.corejava.Arrays;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays数组工具类
 * Arrays.asList() 方法返回的并不是 java.util.ArrayList ，
 * 而是 java.util.Arrays 的一个内部类,这个内部类并没有实现集合的修改方法或者说并没有重写这些方法。
 *
 * @author LQ
 * @date 2020/07/30 11:49
 */
public class ArraysTest {
    public static void main(String[] args) {
        //传递的数组必须是对象数组，而不是基本类型
        int[] ints = {1, 2, 3};
        List lists = Arrays.asList(ints);
        System.out.println(lists.get(0));
//        System.out.println(lists.get(1));
        System.out.println("--------------------------------");

        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(integers.get(1));
        integers.set(1, 6);
//        Arrays.asList没有重写add/remove
//        integers.add(4);
//        integers.remove(1);
        System.out.println(integers);
    }

}

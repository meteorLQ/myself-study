package com.lq.study.corejava.Container;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * fail-fast 快速失败机制
 * 不可以在foreach中进行元素的删除
 * 并发修改异常
 * Exception in thread "main" java.util.ConcurrentModificationException
 * 建议使用迭代器的remove方法
 *
 * @author LQ
 * @date 2020/07/06 14:33
 */
public class FailFast {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println("移除之前" + list.size());
//        for (Integer i : list) {
//            if (i == 2) {
//                list.remove(i);
//            }
//        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 2) {
                iterator.remove();
            }
        }
        System.out.println("移除之后" + list.size());
    }
}

package com.lq.study.corejava.Container.Set.TreeSet;

import java.util.TreeSet;

/**
 * 底层是红黑树结构,即TreeMap,可以进行排序,不允许放入null
 *
 * @author LQ
 * @date 2020/07/30 10:35
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<Object> objects = new TreeSet<>();
        objects.add(1);
        objects.add(null);
    }

}

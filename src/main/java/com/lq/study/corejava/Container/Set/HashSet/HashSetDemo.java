package com.lq.study.corejava.Container.Set.HashSet;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashSet
 *
 * @author LQ
 * @date 2020/07/30 10:35
 */
public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<Object> objects = new HashSet<>();
        objects.add(null);
        objects.add(null);
        objects.add(null);
        objects.add(null);
        TreeSet treeSet = new TreeSet();
//        treeSet.add(null);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1", "2");

        int i = 0;
        System.out.println(i++);
        System.out.println(i);
        System.out.println(++i);
        System.out.println(i);
    }

}

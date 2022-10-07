package com.lq.study.corejava.Container.List.ArrayList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 遍历方式
 *
 * @author LQ
 * @date 2020/07/30 10:35
 */
public class IteratorTest {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        objects.add(5);
        System.out.println("--------遍历方式一-------------");
        // 遍历方式一
        for (int i = 0; i < objects.size(); i++) {
            System.out.println(objects.get(i));
        }
        System.out.println("--------遍历方式一-------------");
        // 遍历方式二
        for (Object object : objects) {
            System.out.println(object);
        }
        System.out.println("---------遍历方式三------------");
        // 遍历方式三
        Iterator<Object> iterator = objects.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}

package com.lq.study.corejava.Container.List.Vector;

import java.util.Vector;

/**
 * Vector
 * jdk老版本的实现list，线程安全的 ，当达到阈值10时扩容机制是原来的2倍
 * 几乎不使用,效率低,占用的空间更大
 *
 * @author LQ
 * @date 2020/07/30 10:35
 */
public class VectorDemo {
    public static void main(String[] args) {
        System.out.println("------------两个参数的构造器----------------------");
        //扩容的容量是 oldCapacity+capacityIncrement
        Vector vector1 = new Vector(1, 0);
        vector1.add(1);
        vector1.add(2);
        vector1.add(3);
        vector1.add(4);
        vector1.add(4);
        vector1.add(4);
        vector1.add(4);
        System.out.println(vector1.capacity());
        System.out.println("-----------单参构造器-----------------------");
        //默认的capacityIncrement是零
        //扩容的容量oldCapacity+oldCapacity
        Vector vector2 = new Vector(1);
        vector2.add(1);
        vector2.add(2);
        vector2.add(3);
        vector2.add(4);
        vector2.add(4);
        vector2.add(4);
        vector2.add(4);
        System.out.println(vector2.capacity());
        System.out.println("-----------无参构造器-----------------------");
        //默认的initialCapacity是10,capacityIncrement是零
        Vector vector3 = new Vector();
        vector3.add(1);
        vector3.add(2);
        vector3.add(3);
        vector3.add(4);
        vector3.add(5);
        vector3.add(6);
        vector3.add(7);
        vector3.add(8);
        vector3.add(9);
        vector3.add(10);
        System.out.println("vector3 capacity = " + vector3.capacity());
        vector3.add(11);
        System.out.println("vector3 capacity = " + vector3.capacity());


    }

}

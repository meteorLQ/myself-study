package com.lq.prototype.deepClone;

/**
 * 原型模式(实现深拷贝和浅拷贝)
 *
 * @author LQ
 * @date 2020/08/23 11:16
 */
public class Prototype {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("多利", 1, "蓝色");
        sheep.fired = new DeepCloneableTarget();
        Sheep clone1 = (Sheep) sheep.clone();
        Sheep clone2 = (Sheep) sheep.clone();
        Sheep clone3 = (Sheep) sheep.deepClone();
        Sheep clone4 = (Sheep) sheep.deepClone();
        System.out.println(sheep + " " + sheep.fired.hashCode());
        System.out.println(clone1 + " " + clone1.fired.hashCode());
        System.out.println(clone2 + " " + clone2.fired.hashCode());
        System.out.println(clone3 + " " + clone3.fired.hashCode());
        System.out.println(clone4 + " " + clone4.fired.hashCode());

    }
}

package com.lq.prototype;

/**
 * 原型模式(实现深拷贝和浅拷贝)
 *
 * @author LQ
 * @date 2020/08/23 11:16
 */
public class Prototype {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("多利", 1, "蓝色");
        sheep=new Sheep("tom",3,"黑色");
//==================================================================
//        Sheep sheep1 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep sheep2 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep sheep3 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep sheep4 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep sheep5 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//=================================================================
        Sheep clone1 = (Sheep) sheep.clone();
        Sheep clone2 = (Sheep) sheep.clone();
//        System.out.println(sheep+" "+sheep.fired.hashCode());
//        System.out.println(clone1+" "+sheep.fired.hashCode());
//        System.out.println(clone2+" "+sheep.fired.hashCode());
        System.out.println(clone1);
        System.out.println(clone2);

    }
}

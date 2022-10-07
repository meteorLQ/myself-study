package com.lq.study.corejava.Thread.single;

/**
 * 饿汉式
 *
 * @author LQ
 * @date 2020/08/03 14:36
 */
public class SingleDemo1 {

    private final static SingleDemo1 singleDemo1 = new SingleDemo1();

    private SingleDemo1() {

    }

    public static SingleDemo1 getInstance() {
        return singleDemo1;
    }

    public static void main(String[] args) {
        System.out.println(SingleDemo1.getInstance() == SingleDemo1.getInstance());

    }
}

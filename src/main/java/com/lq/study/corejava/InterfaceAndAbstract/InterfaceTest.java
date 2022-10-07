package com.lq.study.corejava.InterfaceAndAbstract;

/**
 * 接口中的成员变量是常量   且是public static final(编译器优化省略)
 * 接口中的方法全是抽象方法 public abstract
 * 可以使用javap -c 来反汇编class查看
 * 接口中不能有实现的方法（注:java8中有默认方法实现）
 * 接口可以继承多个接口
 * 接口方法默认是public 不能修改
 * 接口不能有构造器
 * 子类使用implements来实现接口中全部的方法
 *
 * @author LQ
 * @date 2020/07/28 16:14
 */
public interface InterfaceTest extends InterfaceA, InterfaceB {
    public static final int i = 0;

    public void test();
    public void test2();

    public static void main(String[] args) {

    }
}

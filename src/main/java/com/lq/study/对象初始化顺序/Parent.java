package com.lq.study.对象初始化顺序;

/**
 * 先执行静态内容(先父类后子类)，然后执行父类非静态，最后执行子类非静态。
 * （非静态包括构造代码块（匿名代码块、实例代码块）和构造函数，构造代码块先执行）
 *
 * @author LQ
 * @date 2020/07/28 17:44
 */
public class Parent {

//    static {
//        System.out.println("父类静态代码块");
//    }

    {
        System.out.println("父类构造代码块");
    }

    public Parent() {
        System.out.println("父类构造函数");
    }

    public static void main(String[] args) {
        new Children();
    }
}

class Children extends Parent {
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类构造代码块");
    }

    public Children() {
        System.out.println("子类构造函数");
    }


}

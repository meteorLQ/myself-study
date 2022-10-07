package com.lq.study.coreJava.Final;

/**
 * final 修饰的类不能被继承
 * final 修饰的变量是常量
 * final 的方法不能被重写,可以重载，但是可以被继承
 *
 * @author LQ
 * @date 2020/08/10 22:20
 */
public class FinalClass {
    // 静态常量
    final static int num = 5;

    public static void main(String[] args) {

    }

    public final void A(String a, String b) {

    }

    public final void A(String a) {

    }

    public final void A() {

    }


}

class FinalClassA extends FinalClass {
    public void B() {
        A();
    }

}



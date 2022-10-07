package com.lq.study.对象初始化顺序;

/**
 * Java对象初始化
 * 静态代码块
 *
 * @author LQ
 * @date 2020/07/28 22:35
 */
public class Derive extends Base {

    {
        System.out.println("Initial Block()" + str);
    }

    static {
        System.out.println("init static");
    }

    private static String str = "123323";

    private static Member m1 = new Member("Member 1" + str);

    public Derive(String str) {
        super(str);
        System.out.println("Derive()");
    }

    private Member m2 = new Member("Member 2" + str);

    private int getInt() {
        System.out.println("getInt()");
        return 2;
    }

    private int i = getInt();


    public static void main(String[] args) {
        new Derive(str);
    }


}

class Base {

    public Base(String str) {
        System.out.println("Base()" + str);
    }
}

class Member {

    public Member(String m) {
        System.out.println("Member() " + m);
    }
}


package com.lq.study.代码块执行顺序;

/**
 * 代码块执行顺序
 * 静态代码块只会执行一次。有多个静态代码块时按顺序依次执行。
 * 构造代码块每次创建新对象时都会执行。有多个时依次执行。
 * 执行顺序：静态代码块 > 构造代码块 > 构造函数。
 * 构造代码块和静态代码块有自己的作用域，作用域内部的变量不影响作用域外部。
 *
 * @author LQ
 * @date 2020/07/28 17:35
 */
public class CodeBlock {
    private int a = 1;
    private int b;
    private int c;

    //静态代码块
    static {
        int a = 4;
        System.out.println("我是静态代码块1");
    }

    //构造代码块
    {
        int a = 0;
        inNum();
        System.out.println("构造代码块1");
    }

    public void inNum() {
        b = 4;
    }

    public CodeBlock() {
        this.c = 3;
        System.out.println("构造函数");
    }

    public int add() {

        System.out.println("count a + b + c");
        return a + b + c;
    }

    //静态代码块
    static {
        System.out.println("我是静态代码块2，我什么也不做");
    }

    //构造代码块
    {
        System.out.println("构造代码块2");
    }

    public static void main(String[] args) {
        CodeBlock c = new CodeBlock();
        System.out.println(c.add());

        System.out.println();
        System.out.println("*******再来一次*********");
        System.out.println();

        CodeBlock c1 = new CodeBlock();
        System.out.println(c1.add());
    }
}


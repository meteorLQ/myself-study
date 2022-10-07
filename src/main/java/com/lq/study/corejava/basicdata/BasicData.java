package com.lq.study.coreJava.basicdata;

/**
 * 基本数据类型
 * int  4个字节    -2^31----2^31-1
 * long  8个字节   -2^63---2^63-1
 * short  2个字节   -2^15----2^15-1
 * double 8个字节   -1.798----1.798
 * float  4个字节   -2^15----2^15-1
 * boolean 1个字节   false TRUE
 * char  2个字节    \u0000(0)-----\uffff(65535)
 * byte  1个字节   -128---127
 *
 * @author LQ
 * @date 2020/08/22 17:58
 */
public class BasicData {
    public static void main(String[] args) {
        /**
         * 1.基本数据类的的父类是什么？包装类的父类是什么？
         * 答:基本数据类型没有父类,包装类的父类是Number
         */
        System.out.println(int.class.getSuperclass());
        System.out.println(Integer.class.getSuperclass());
    }

}

package com.lq.study.coreJava.trycatchfinally;

/**
 * 当在try 或catch中遇到return  finally会在返回之前执行,如果finally中也有return,那么finally中的返回值会覆盖掉之前的返回值
 * 如果在finally的第一行出现异常，finally下面不会执行
 *
 * @author LQ
 * @date 2020/08/23 15:22
 */
public class TryCatchFinally {
    public static int num() {
        try {
            int i = 1 / 1;
//            int i = 1 / 0;
            System.out.println("run被执行了");
            return i;
        } catch (Exception e) {
            System.out.println("异常被执行了");
            return 1;
            // e.printStackTrace();
        } finally {

            System.out.println("finally被执行了");
            return 0;
        }



    }

    public static void main(String[] args) {
        int num = num();
        System.out.println(num);
    }
}

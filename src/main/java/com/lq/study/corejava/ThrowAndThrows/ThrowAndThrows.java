package com.lq.study.corejava.ThrowAndThrows;

/**
 * throw 关键字用来在程序中明确的抛出异常，相反，
 * throws 语句用来表明方法不能处理的异常。每一个方法都必须要指定哪些异常不能处理，
 * 所以方法的调用者才能够确保处理可能发生的异常，多个异常是用逗号分隔的。
 *
 * @author LQ
 * @date 2020/07/28 23:03
 */
public class ThrowAndThrows {


    public void run() throws Exception {
        throw new RuntimeException("抛出一个异常");
    }


    public static void main(String[] args) {
        try {
            new ThrowAndThrows().run();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}

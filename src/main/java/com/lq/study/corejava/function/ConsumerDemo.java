package com.lq.study.corejava.function;

import java.util.function.Consumer;

/**
 * 函数式接口之消费型接口
 *
 * @author LQ
 * @date 2020/08/02 21:53
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer consumer = str -> {
            System.out.println(str);
        };

        consumer.accept("hello world !");
    }
}

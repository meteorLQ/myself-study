package com.lq.study.corejava.function;

import java.util.function.Function;

/**
 * 函数型接口
 *
 * @author LQ
 * @date 2020/08/02 21:54
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer, Integer> function = num -> {
            num++;
            return num;
        };

        System.out.println(function.apply(1));
    }
}

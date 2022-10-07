package com.lq.study.corejava.function;

import java.util.function.Predicate;

/**
 * 断定型函数式接口
 *
 * @author LQ
 * @date 2020/08/02 21:55
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<Object> predicate = (o) -> {
            return o == null;
        };

        System.out.println(predicate.test(1));
    }

}

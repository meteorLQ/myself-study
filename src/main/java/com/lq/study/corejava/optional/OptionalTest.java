package com.lq.study.coreJava.optional;

import java.util.Optional;

/**
 * @author LQ
 * @date 2020/10/12 10:48
 */
public class OptionalTest {
    public static void main(String[] args) {
        // 不允许为空
//        Optional<Object> empty1 = Optional.of(null);
        // 如何为空创建一个空的Optional实例
        Optional<Object> empty2 = Optional.ofNullable(null);
        // 创建一个空Optional实例
        Optional<Object> empty = Optional.empty();
        System.out.println(empty);

        Optional<String> s = Optional.ofNullable("llllll");
        Optional<String> s2 = s.filter(x -> x.length() > 0);
        String s1 = s.orElseGet(() -> {
            int i = 1;
            int j = 2;
            return String.valueOf(i + j);
        });
        Optional<String> kkkkk1 = s.map((a) -> {
            String kkkkk = a.concat("KKKKK");
            return kkkkk;
        });
        System.out.println(kkkkk1);
        Optional<Optional<String>> kkkkk = s.flatMap((a1) -> {
            String kkkkk11 = a1.concat("KKKKK");
            return Optional.of(kkkkk1);
        });
        System.out.println(kkkkk.get());

    }
}

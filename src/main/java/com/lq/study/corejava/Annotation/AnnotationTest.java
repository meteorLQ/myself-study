package com.lq.study.corejava.Annotation;

import java.util.ArrayList;

/**
 * 自定义注解
 *
 * @author LQ
 * @date 2020/06/30 21:59
 */
public class AnnotationTest {
    public static void main(String[] args) {

        int n = 1;
    }
}


class Person<E> {

    private String name;

    private
    int age;

    public <@MyAnnotation("1") T> void add(T e) {
        new ArrayList<>();
    }
}

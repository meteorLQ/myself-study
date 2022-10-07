package com.lq.study.coreJava.reflect;

import java.lang.reflect.ReflectPermission;

/**
 * 反射 (目标类)
 *
 * @author LQ
 * @date 2020/08/20 22:25
 */
public class TargetObject {
    private String name;
    private Integer age;


    public TargetObject() {
        name = "LQ";
    }

    public void publicMethod(String s) {
        System.out.println("public I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + name);
    }
}


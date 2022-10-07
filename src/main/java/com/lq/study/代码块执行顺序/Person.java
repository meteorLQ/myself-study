package com.lq.study.代码块执行顺序;

/**
 *
 * @author LQ
 * @date 2020/07/28 18:48
 */
public class Person {
    private Integer age;
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

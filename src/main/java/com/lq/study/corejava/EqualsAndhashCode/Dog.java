package com.lq.study.corejava.EqualsAndhashCode;

import java.util.Objects;

/**
 * @author LQ
 * @date 2020/07/29 18:33
 */
public class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Dog dog = (Dog) o;
        return Objects.equals(getName(), dog.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public static void main(String[] args) {
        Dog dag1 = new Dog("天天");
        Dog dag2 = new Dog("天天");

        System.out.println(dag1.equals(dag2));
        System.out.println(dag1 == dag2);
    }
}

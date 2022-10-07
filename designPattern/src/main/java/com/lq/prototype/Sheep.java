package com.lq.prototype;

import com.lq.prototype.deepClone.DeepCloneableTarget;

/**
 * 克隆羊
 *
 * @author LQ
 * @date 2020/08/23 11:27
 */
public class Sheep implements Cloneable{
    private String name;
    private Integer age;
    private String color;

    //public DeepCloneableTarget deepCloneableTarget;

    public Sheep() {
    }

    public Sheep(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    protected Object clone()  {
        Sheep sheep=null;
        try {
            sheep= (Sheep) super.clone();
            //sheep.deepCloneableTarget= (DeepCloneableTarget) deepCloneableTarget.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheep;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}

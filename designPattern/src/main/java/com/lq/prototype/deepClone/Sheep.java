package com.lq.prototype.deepClone;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * 克隆羊
 *
 * @author LQ
 * @date 2020/08/23 11:27
 */
public class Sheep implements Cloneable, Serializable {
    private String name;
    private Integer age;
    private String color;

    public DeepCloneableTarget fired;

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

    /**
     * 深拷贝通过clone方法实现
     *
     * @return
     */
    @Override
    protected Object clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
            sheep.fired = (DeepCloneableTarget) fired.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheep;
    }

    /**
     * 深拷贝 通过序列化的方式实现
     *
     * @return
     */
    public Object deepClone() {
        ByteOutputStream byteOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 序列化
            byteOutputStream = new ByteOutputStream();
            objectOutputStream = new ObjectOutputStream(byteOutputStream);
            objectOutputStream.writeObject(this);
            // 反序列化
            byteArrayInputStream = new ByteArrayInputStream(byteOutputStream.getBytes());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object o = objectInputStream.readObject();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
                byteArrayInputStream.close();
                byteOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
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

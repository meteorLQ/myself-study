package com.lq.study.coreJava.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.ArrayList;

/**
 * @author LQ
 * @date 2020/08/20 22:32
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception, IllegalAccessException, InstantiationException, NoSuchMethodException {



        // 获取TargetObject类的Class对象并且创建TargetObject类实例
        Class<?> aClass = Class.forName("com.lq.study.coreJava.reflect.TargetObject");
        // 创建该类的实例对象
        Object o = aClass.newInstance();
        // 获取全部声明的方法
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        // 获取指定方法并执行
        Method publicMethod = aClass.getDeclaredMethod("publicMethod", String.class);
        TargetObject targetObject = new TargetObject();
        //targetObject.publicMethod("LQ");
        publicMethod.invoke(o, "LQ");
        // 获取声明的所有属性
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        // 修改私有的变量
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(o, "doudou");

        // 调用私有的方法
        Method privateMethod = aClass.getDeclaredMethod("privateMethod");
        // 取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(o);


    }
}

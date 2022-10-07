package com.lq.study.corejava.Thread.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 饿汉式
 * DCL双重验证
 *
 * @author LQ
 * @date 2020/08/03 14:36
 */
public class SingleDemo2 {

    private static boolean flag = false;
    //volatile防止指令重排
    private static volatile SingleDemo2 singleDemo2 = null;

    private SingleDemo2() {
        if (flag == false) {
            flag = true;
        } else {
            throw new RuntimeException("禁止使用反射破坏单例！");
        }
    }

    public static SingleDemo2 getInstance() {
        if (singleDemo2 == null) {
            synchronized (SingleDemo2.class) {
                if (singleDemo2 == null) {
                    singleDemo2 = new SingleDemo2();
                }
                return singleDemo2;
            }
        }
        return singleDemo2;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //初始化一个线程池
//        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();
//        try {
//            for (int i = 0; i < 10; i++) {
//                threadPoolExecutor.execute(() -> {
//                    SingleDemo2 instance = SingleDemo2.getInstance();
//                    System.out.println(Thread.currentThread().getName() + "===>>>" + instance);
//                });
//            }
//        } finally {
//            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
//        }
        //破坏单例通过反射
        //SingleDemo2 instance1 = SingleDemo2.getInstance();
        Class singleDemo2Class = Class.forName("com.lq.study.corejava.Thread.single.SingleDemo2");
        Constructor<SingleDemo2> declaredConstructor = singleDemo2Class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        Field flag = singleDemo2Class.getDeclaredField("flag");
        flag.setAccessible(true);

        SingleDemo2 instance2 = declaredConstructor.newInstance();
        flag.set(instance2, false);

        SingleDemo2 instance1 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}

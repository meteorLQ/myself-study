package com.lq.study.corejava.Thread.single;

import java.lang.reflect.InvocationTargetException;

/**
 * 通过枚举防止反射破坏单例
 * Java不支持枚举反射 Cannot reflectively create enum objects
 *
 * @author LQ
 * @date 2020/08/03 14:36
 */
public enum SingleDemo3 {
    SINGLEDEMO3;


    public static SingleDemo3 getInstance() {
        return SingleDemo3.SINGLEDEMO3;
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

        SingleDemo3 instance1 = SingleDemo3.getInstance();
        SingleDemo3 instance2 = SingleDemo3.getInstance();
        System.out.println(instance1);
        System.out.println(instance2);
        //破坏单例通过反射
        //SingleDemo2 instance1 = SingleDemo2.getInstance();
//        Class singleDemo2Class = Class.forName("com.lq.study.coreJava.Thread.single.SingleDemo3");
//        Constructor<SingleDemo3> declaredConstructor = singleDemo2Class.getDeclaredConstructor(String.class, int.class);
//        declaredConstructor.setAccessible(true);
//        SingleDemo3 instance2 = declaredConstructor.newInstance();
//        SingleDemo3 instance1 = declaredConstructor.newInstance();
//        System.out.println(instance1);
//        System.out.println(instance2);
    }
}

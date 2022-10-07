package com.lq.study.corejava.Thread.集合不安全.Map.HashMap;

import com.lq.study.util.ThreadPoolUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * HashMap线程不安全
 * 解决方法
 * 1. Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
 * 2.HashTable   (几乎不会使用，因为其使用synchronized效率实在有点低,默认是10,每次需要扩容为原来的2倍)
 * 3.ConcurrentHashMap推荐使用 底层使用synchronized+cas+数组链表/红黑树
 *
 * @author LQ
 * @date 2020/07/30 23:48
 */
public class HashMapDemo {
    public static void main(String[] args) {
        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();
//        HashMap<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
//        Hashtable<String, String> map = new Hashtable<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        try {
            for (int i = 0; i < 100; i++) {
                threadPoolExecutor.execute(() -> {
                    map.put(UUID.randomUUID().toString().substring(0, 5), UUID.randomUUID().toString().substring(0, 5));
                    System.out.println(map);
                });
            }
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }
    }
}

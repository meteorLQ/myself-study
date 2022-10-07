package com.lq.study.corejava.Thread.集合不安全.List.ArrayList;

import com.lq.study.util.ThreadPoolUtil;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试ArrayList线程不安全
 * 解决方法
 * 1.Vector<String> list = new Vector<>();(同步的集合，效率低下，因为每次扩容为原来的两倍)
 * 2.Collections.synchronizedList(new ArrayList<>())
 * 3.new CopyOnWriteArrayList<>()
 *
 * @author LQ
 * @date 2020/07/30 23:34
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        //初始化一个线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.initPool();


//         ArrayList<String> list = new ArrayList<>();
        // Vector<String> list = new Vector<>();
//         List<String> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        try {
            for (int i = 0; i < 20; i++) {
                threadPoolExecutor.execute(() -> {
                    list.add(UUID.randomUUID().toString().substring(0, 5));
                    System.out.println(list);
                });
            }
        } finally {
            ThreadPoolUtil.shutdownAndAwaitTermination(threadPoolExecutor);
        }
    }
}

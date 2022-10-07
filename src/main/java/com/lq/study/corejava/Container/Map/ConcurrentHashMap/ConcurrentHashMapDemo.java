package com.lq.study.corejava.Container.Map.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LQ
 * @date 2020/07/31 18:42
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "A");
    }
}

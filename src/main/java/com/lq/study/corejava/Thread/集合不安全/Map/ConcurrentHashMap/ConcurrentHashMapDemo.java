package com.lq.study.corejava.Thread.集合不安全.Map.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LQ
 * @date 2020/07/31 18:42
 */
public class ConcurrentHashMapDemo {
    static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new Thread(() -> {
            ArrayList<String> user = new ArrayList<>();
            user.add("user1");
            user.add("user2");
            List<String> topic1 = map.putIfAbsent("topic1", user);
            if (null != topic1) {
                topic1.addAll(user);
            }
            System.out.println(JSON.toJSONString(map));
        }).start();

        new Thread(() -> {
            ArrayList<String> user = new ArrayList<>();
            user.add("user11");
            user.add("user22");
            map.putIfAbsent("topic1", user);
            List<String> topic1 = map.putIfAbsent("topic1", user);
            if (null != topic1) {
                topic1.addAll(user);
            }
            System.out.println(JSON.toJSONString(map));
        }).start();

        new Thread(() -> {
            ArrayList<String> user = new ArrayList<>();
            user.add("user111");
            user.add("user222");
            List<String> topic2 = map.putIfAbsent("topic2", user);
            if (null != topic2) {
                topic2.addAll(user);
            }
            System.out.println(JSON.toJSONString(map));
        }).start();


    }
}

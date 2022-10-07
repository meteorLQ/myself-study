package com.lq.study.corejava.Thread;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author LQ
 * @date 2020/03/23 23:54
 */
public class TestList {
    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        new Hashtable();


//        CopyOnWriteArraySet<String> objects = new CopyOnWriteArraySet<>();
//        HashSet<String> strings = new HashSet<>();
//        Collections.synchronizedSet(strings);
//        for (int i = 0; i < 30; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    objects.add(UUID.randomUUID().toString().substring(0,8));
//                    System.out.println(objects);
//                }
//            }, "线程" + i).start();
//        }


//        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
//        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    copyOnWriteArrayList.add(UUID.randomUUID().toString().substring(0,8));
//                    System.out.println(copyOnWriteArrayList);
//                }
//            }, "线程" + i).start();
//        }


    }
}

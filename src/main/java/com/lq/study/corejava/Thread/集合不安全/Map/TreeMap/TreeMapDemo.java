package com.lq.study.corejava.Thread.集合不安全.Map.TreeMap;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * 底层是红黑树 不允许为null
 *
 * @author LQ
 * @date 2020/07/31 17:46
 */
public class TreeMapDemo {
    private Integer age;

    public TreeMapDemo(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public static void main(String[] args) {
        TreeMap<TreeMapDemo, String> treeMap = new TreeMap<>(new Comparator<TreeMapDemo>() {
            @Override
            public int compare(TreeMapDemo person1, TreeMapDemo person2) {
                int num = person1.getAge() - person2.getAge();
                return Integer.compare(num, 0);
            }
        });
        treeMap.put(new TreeMapDemo(3), "person1");
        treeMap.put(new TreeMapDemo(18), "person2");
        treeMap.put(new TreeMapDemo(35), "person3");
        treeMap.put(null, "person3");
        treeMap.put(new TreeMapDemo(16), "person4");
        treeMap.entrySet().stream().forEach(personStringEntry -> {
            System.out.println(personStringEntry.getValue());
        });
    }
}

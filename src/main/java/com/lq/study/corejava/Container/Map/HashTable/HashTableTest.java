package com.lq.study.corejava.Container.Map.HashTable;

import java.util.Hashtable;

/**
 * Hashtable(同一把锁) :使用 synchronized 来保证线程安全，效率非常低下。
 * 当一个线程访问同步方法时，其他线程也访问同步方法，
 * 可能会进入阻塞或轮询状态，如使用 put 添加元素，
 * 另一个线程不能使用 put 添加元素，也不能使用 get，竞争会越来越激烈效率越低。
 *
 * k v 不能为null
 * <p>
 * Hashtable 和 JDK1.8 之前的 HashMap 的底层数据结构类似都是采用 数组+链表 的形式，
 * 数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的；
 *
 * @author LQ
 * @date 2020/07/29 20:36
 */
public class HashTableTest {
    public static void main(String[] args) {
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put(1, "A");
        hashtable.put(null, "1");
    }
}

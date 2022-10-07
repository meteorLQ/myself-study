package com.lq.study.corejava.Thread.集合不安全.Map.HashMap;

/**
 * @author LQ
 * @date 2020/07/29 9:44
 */
public class MyEntry<K,V> extends Node<K,V> {
    MyEntry<K,V> before, after;

    MyEntry(int hash, K key, V value, Node<K, V> next) {
        super(hash, key, value, next);
    }
}

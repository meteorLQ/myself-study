package com.lq.study.corejava.Thread.集合不安全.Map.HashMap;

/**
 * @author LQ
 * @date 2020/07/29 9:43
 */
public class Node<K,V>  {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }


}

package com.lq.study.corejava.optional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LQ
 * @date 2020/10/30 11:14
 */
public class NullTest {
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 99999) ? 99999 : n + 1;
    }

    public static void main(String[] args) {
        Map<String, Boolean> map = new HashMap<String, Boolean>(1);
        Boolean b = (map != null ? map.get("test") : Boolean.FALSE);
        System.out.println(b);
        int i = tableSizeFor(1);
        System.out.println(i);

    }


}

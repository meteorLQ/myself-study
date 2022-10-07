package com.lq.jdk15;

import org.junit.jupiter.api.Test;

/**
 * JEP 375：Pattern Matching for instanceof (Second Preview) instanceof 自动匹配模式
 *
 * @author LQ
 * @date 2020/10/22 19:46
 */
public class InstanceofTest {
    @Test
    void test1() {
        Object o = new String("你好");
        if (o instanceof String) {
            String a = (String) o;
            System.out.println(a);
        }
    }

    @Test
    void test2() {
        Object o = new String("你好");
        // 不需要强制类型转化
        if (o instanceof String a) {
            System.out.println(a);
        }
    }
}

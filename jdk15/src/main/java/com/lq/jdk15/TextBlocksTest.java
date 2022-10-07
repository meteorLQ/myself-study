package com.lq.jdk15;

import org.junit.jupiter.api.Test;

/**
 * JEP 378：文本块功能转正
 *
 * @author LQ
 * @date 2020/10/22 20:45
 */
public class TextBlocksTest {
    @Test
    void test1() {
        System.out.println("""
                 nacos.naming.empty-service.auto-clean=true
                 nacos.naming.empty-service.clean.initial-delay-ms=50000
                 nacos.naming.empty-service.clean.period-time-ms=30000               
                """.length());
    }

    @Test
    void test2() {
        System.out.println(
                "nacos.naming.empty-service.auto-clean=true" +
                        "nacos.naming.empty-service.clean.initial-delay-ms=50000\n" +
                        "nacos.naming.empty-service.clean.period-time-ms=30000"
        );
    }
}

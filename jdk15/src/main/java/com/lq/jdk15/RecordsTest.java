package com.lq.jdk15;

/**
 * JEP 384：Records Class（预览）
 *
 * @author LQ
 * @date 2020/10/23 9:25
 */
public record RecordsTest(String name, String id) {
    public static void main(String[] args) {
        RecordsTest recordsTest = new RecordsTest("李四", null);

        System.out.println(recordsTest);
        System.out.println(recordsTest.name());
    }
}

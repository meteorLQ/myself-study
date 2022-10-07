package com.lq.study.corejava.function;

import java.util.function.Supplier;

/**
 * 供给型接口
 *
 * @author LQ
 * @date 2020/08/02 22:19
 */
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<Integer> supplier = () -> {
            return 1024;
        };

        System.out.println(supplier.get());
    }
}

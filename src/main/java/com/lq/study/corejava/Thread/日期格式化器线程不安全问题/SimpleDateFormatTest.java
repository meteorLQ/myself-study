package com.lq.study.coreJava.Thread.日期格式化器线程不安全问题;

import java.text.ParseException;
import java.util.Date;

/**
 * @author LQ
 * @date 2020/09/11 10:23
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Date parse = DateUtil.parse("2013-05-24 06:02:20");
                    System.out.println(parse);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}

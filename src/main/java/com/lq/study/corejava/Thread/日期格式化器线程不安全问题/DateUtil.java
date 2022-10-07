package com.lq.study.coreJava.Thread.日期格式化器线程不安全问题;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LQ
 * @date 2020/09/11 10:04
 */
public class DateUtil {
    private static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String formatDate(Date date) {
        return sdf.get().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return sdf.get().parse(strDate);

    }
}

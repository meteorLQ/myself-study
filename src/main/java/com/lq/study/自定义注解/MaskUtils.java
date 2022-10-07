package com.lq.study.自定义注解;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LQ
 * @date 2020/09/28 19:13
 */
public final class MaskUtils {
    private static final Logger logger = LoggerFactory.getLogger(MaskUtils.class);

    /**
     * List<object> 打码
     *
     * @param obj
     * @param <E>
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <E> List<E> maskListObject(List<E> obj) throws InstantiationException, IllegalAccessException {
        if (null == obj || obj.isEmpty()) {
            return new ArrayList<>();
        }
        List<E> maskList = new ArrayList<>();
        for (E e : obj) {
            maskObject(e);
            maskList.add(e);
        }

        return maskList;

    }

    /**
     * 打码
     *
     * @param obj
     * @param <E>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <E> E maskObject(E obj) {
        //logger.info("打码前:{}", JsonUtil.toJSONString(obj));
        if (null == obj) {
            return null;
        }
        Class aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            Sensitive annotation = field.getAnnotation(Sensitive.class);
            if (null == annotation || !field.getType().equals(String.class)) {
                continue;
            }
            if (annotation.mask()) {

                String value = null;
                try {
                    value = (String) field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (StringUtils.isEmpty(value)) {
                    continue;
                }
                int suffix = annotation.suffixLength();
                int prefix = annotation.prefixLength();
                String character = annotation.sensitiveChar();
                try {
                    field.set(obj,
                            maskString(value, produceCharacter(value.length() - prefix - suffix, character), prefix,
                                    suffix));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

        // logger.info("打码后:{}", JsonUtil.toJSONString(obj));
        return obj;
    }

    public static String maskString(String id, String replacement, int prefix, int suffix) {
        if (null == id || "".equals(id)) {
            return "";
        }
        if (id.length() < prefix + suffix) {
            throw new IllegalArgumentException("明文过短，无法脱敏");
        }
        String value = id.replace(id.substring(prefix, id.length() - suffix), replacement);

        return value;
    }

    public static String maskPhone(String mobilePhone) {

        return maskString(mobilePhone, "*", 3, 4);
    }

    public static String produceCharacter(int len, String character) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb = sb.append(character);
        }
        return sb.toString();

    }

    private MaskUtils() {
    }
}

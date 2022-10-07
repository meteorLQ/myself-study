package com.lq.study.corejava.Annotation;

import java.lang.annotation.*;

/**
 * @author LQ
 * @date 2020/06/30 22:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE_PARAMETER)
public @interface MyAnnotations {
    MyAnnotation[] value();
}
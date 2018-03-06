package com.example.houyongliang.annotationtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by houyongliang on 2018/3/2.
 */
//@Target(ElementType.TYPE)
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    String value();//注解中只有一个属性，可以直接命名为“value”，使用时无需再标明属性名。
}

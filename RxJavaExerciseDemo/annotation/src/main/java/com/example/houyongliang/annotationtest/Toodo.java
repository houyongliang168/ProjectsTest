package com.example.houyongliang.annotationtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by houyongliang on 2018/3/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Toodo {
    public enum Priority{LOW,MEDIUM,HIGH}
    public  enum Status{STATUSED,NOT_ATATUSED}
    String auther() default "Yash";
    Priority priorty() default Priority.LOW;
    Status status() default Status.NOT_ATATUSED;
}

package com.example.houyongliang.annotationtest;

/**
 * Created by houyongliang on 2018/3/2.
 */
@Author("HOUYL")
public class Logic {
    //注解的使用需要 反射机制
    @Toodo(priorty = Toodo.Priority.MEDIUM, auther = "houyl", status = Toodo.Status.STATUSED)
    public void incompleteMethod1() {

    }

    public void someMethod() {

    }
    @Author("hhhhyl")
    public String tst="adfadf";
    @Author("hylllll")
    public String tsts;

}

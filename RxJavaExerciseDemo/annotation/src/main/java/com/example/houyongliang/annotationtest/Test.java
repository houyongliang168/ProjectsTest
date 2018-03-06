package com.example.houyongliang.annotationtest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by houyongliang on 2018/3/2.
 */

public class Test {
    @Nullable
    public static String obtainReferrerFromIntent(@NonNull Intent intent){
        return intent.getStringExtra("apps_referrer");
    }





    public static void main(String[] args) {
        obtainReferrerFromIntent(null);



        Class<Logic> clazz=Logic.class;
        //获取方法的注解信息
        //如果存在 得到 @Toodo 的信息并打印
        for (Method method : clazz.getMethods()) {//遍历类中所有方法
            Toodo toodoAnnotation =(Toodo) method.getAnnotation(Toodo.class);
            if(toodoAnnotation!=null){
                System.out.println(" Method Name : " + method.getName());
                System.out.println(" Author : " + toodoAnnotation.auther());
                System.out.println(" Priority : " + toodoAnnotation.priorty());
                System.out.println(" Status : " + toodoAnnotation.status());
            }
        }
        //获取类注解的信息

        ////如果存在,得到@Author的信息并打印
        if(clazz.isAnnotationPresent(Author.class)){
            Author author=  clazz.getAnnotation(Author.class);//获取类注解
            System.out.println("测试信息如下:");
            System.out.println("Author :"+author.value());
        }

        for (Field field : clazz.getFields()) {
            Author author=field.getAnnotation(Author.class);
            if(author!=null){
                System.out.println("Author 2:"+author.value());
            }

        }

        for (Field field : clazz.getDeclaredFields()) {
            Author author=field.getAnnotation(Author.class);
            if(author!=null){
                System.out.println("Author 3:"+author.value());
            }

        }
        //getDeclaredFields 与 getFields 效果相同


    }

}

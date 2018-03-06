package com.example.houyongliang.annotationtest;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by houyongliang on 2018/3/2.
 */

public class Colors {
    @IntDef({RED,GREEN,YELLOW})//@IntDef 常用来代替枚举
//    @Retention(RetentionPolicy.RUNTIME)//整个运行期间
    @Retention(RetentionPolicy.SOURCE)//注解仅存在与源码中,不加入到class文件中
    public  @interface LightColors{}
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int YELLOW = 2;

}

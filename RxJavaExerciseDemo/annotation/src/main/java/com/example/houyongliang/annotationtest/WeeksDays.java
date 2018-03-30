package com.example.houyongliang.annotationtest;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by houyongliang on 2018/3/12 10:48.
 * Function(功能):
 */

public class WeeksDays {
    @WeekDays int currentDay = SUNDAY;/*默认为 周日*/

    //先定义 常量
    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    //用 @IntDef "包住" 常量；
    // @Retention 定义策略
    // 声明构造器
    @IntDef({SUNDAY, MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY})
    @Retention(RetentionPolicy.SOURCE)
    public  @interface WeekDays{

    }

    public void setCurrentDay(@WeekDays int currentDay) {
        this.currentDay = currentDay;
    }

    @WeekDays
    public int getCurrentDay() {
        return currentDay;
    }

}

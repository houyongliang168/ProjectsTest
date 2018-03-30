package com.example.houyongliang.testdemo.junit;

import com.example.houyongliang.testdemo.junit.DateUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by houyongliang on 2018/3/21 11:06.
 * Function(功能):  连续测试 参数的方式
 */
@RunWith(Parameterized.class)
public class DateFormatTest {
    private String time;//返回的类型与 primeNumbers 方法中返回Collection 中的类型相同

    public DateFormatTest(String time) {
        this.time = time;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new String[]{
                "2015-10-15",
                "2017-10-15 16:00:02",
                "2017年10月15日  16时00分02秒"
        });
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception {
        DateUtil.dateToStamp(time);
    }


}

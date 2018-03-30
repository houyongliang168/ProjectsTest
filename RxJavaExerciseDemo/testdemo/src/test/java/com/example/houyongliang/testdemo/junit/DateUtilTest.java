package com.example.houyongliang.testdemo.junit;

import com.example.houyongliang.testdemo.junit.DateUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by houyongliang on 2018/3/21 10:33.
 * Function(功能):
 */

public class DateUtilTest {
    private String time = "2017-10-15 16:00:02";
    private long timeStamp = 1508054402000L;
    private Date mDate;

    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void setDown() throws Exception{
        System.out.println("测试结束");
    }

    //    assertEquals(期望值, 测试值);
    @Test
    public void stampToDateTest() throws Exception{
        assertEquals("预期时间", DateUtil.stampToDate(timeStamp));
//        assertNotEquals("预期时间", DateUtil.stampToDate(timeStamp));
//        assertNotEquals(time, DateUtil.stampToDate(timeStamp));
    }

    @Test
    public void dateToStamp() throws Exception{
        assertNotEquals(timeStamp,DateUtil.dateToStamp(time));
//        assertEquals(4,DateUtil.dateToStamp(time));
    }

}

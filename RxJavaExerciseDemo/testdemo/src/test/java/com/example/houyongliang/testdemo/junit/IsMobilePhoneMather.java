package com.example.houyongliang.testdemo.junit;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by houyongliang on 2018/3/21 13:28.
 * Function(功能):
 * assertThat 测试 自定义匹配
 */

public class IsMobilePhoneMather {
    @Rule
    public MyRule rule = new MyRule();

    @Test
    public void testMobilePhone() {
//        assertThat("19800000000",new IsMobilePhoneMatcher());
        assertThat("18600000000", new IsMobilePhoneMatcher());
    }

    @Test
    public void testMobilePhone2() {
        assertThat("19700000000", new IsMobilePhoneMatcher());
    }

}

package com.example.houyongliang.testdemo.junit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Created by houyongliang on 2018/3/21 11:24.
 * Function(功能):.assertThat用法
 */

public class AssertThatTest {
    /**
     * assertThat(T actual, Matcher<? super T> matcher);
     * assertThat(String reason, T actual, Matcher<? super T> matcher);
     * 其中reason为断言失败时的输出信息，actual为断言的值，matcher为断言的匹配器。
     */


/*匹配器	说明	例子
    is	断言参数等于后面给出的匹配表达式	assertThat(5, is (5));
    not	断言参数不等于后面给出的匹配表达式	assertThat(5, not(6));
    equalTo	断言参数相等	assertThat(30, equalTo(30));
    equalToIgnoringCase	断言字符串相等忽略大小写	assertThat(“Ab”, equalToIgnoringCase(“ab”));
    containsString	断言字符串包含某字符串	assertThat(“abc”, containsString(“bc”));
    startsWith	断言字符串以某字符串开始	assertThat(“abc”, startsWith(“a”));
    endsWith	断言字符串以某字符串结束	assertThat(“abc”, endsWith(“c”));
    nullValue	断言参数的值为null	assertThat(null, nullValue());
    notNullValue	断言参数的值不为null	assertThat(“abc”, notNullValue());
    greaterThan	断言参数大于	assertThat(4, greaterThan(3));
    lessThan	断言参数小于	assertThat(4, lessThan(6));
    greaterThanOrEqualTo	断言参数大于等于	assertThat(4, greaterThanOrEqualTo(3));
    lessThanOrEqualTo	断言参数小于等于	assertThat(4, lessThanOrEqualTo(6));
    closeTo	断言浮点型数在某一范围内	assertThat(4.0, closeTo(2.6, 4.3));
    allOf	断言符合所有条件，相当于&&	assertThat(4,allOf(greaterThan(3), lessThan(6)));
    anyOf	断言符合某一条件，相当于或	assertThat(4,anyOf(greaterThan(9), lessThan(6)));
    hasKey	断言Map集合含有此键	assertThat(map, hasKey(“key”));
    hasValue	断言Map集合含有此值	assertThat(map, hasValue(value));
    hasItem	断言迭代对象含有此元素	assertThat(list, hasItem(element));*/
    @Test
    public void testAssertThat1() throws Exception {

        assertThat("Hello UT",both(startsWith("Hell")).and(endsWith("AUT")));

    }


}

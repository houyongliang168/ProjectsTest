package com.example.houyongliang.testdemo.mockito;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by houyongliang on 2018/3/21 14:44.
 * Function(功能):
 */

public class MockTest {
//    普通方法：

    @Test
    public void testIsNotNull(){
        Person mPerson = mock(Person.class); //<--使用mock方法
//        assertNull(mPerson);
        assertNotNull(mPerson);
    }


}

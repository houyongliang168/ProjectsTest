package com.example.houyongliang.testdemo.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * Created by houyongliang on 2018/3/21 15:54.
 * Function(功能):
 */

public class MockitoAnnotationsTest {
    //注解方法：
    @Mock //<--使用@Mock注解
    Person mPerson;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //<--初始化
    }

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

}

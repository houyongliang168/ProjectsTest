package com.example.houyongliang.testdemo.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by houyongliang on 2018/3/21 15:55.
 * Function(功能):
 */
@RunWith(MockitoJUnitRunner.class) //<--使用MockitoJUnitRunner
public class MockitoJUnitRunnerTest {
    //运行器方法
    @Mock //<--使用@Mock注解
            Person mPerson;

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }



}

package com.example.houyongliang.testdemo.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by houyongliang on 2018/3/21 15:36.
 * Function(功能):
 */

public class PresenterTest {

    Model     model;
    Presenter presenter;

    @Before
    public void setUp() throws Exception {
        model = mock(Model.class);// mock Model对象

        presenter = new Presenter(model);
    }

    @Test
    public void testGetBoolean() throws Exception {
        when(model.getBoolean()).thenReturn(true);
        Assert.assertTrue(presenter.getBoolean());
    }



}

package com.example.houyongliang.testdemo.PowerMock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * Created by houyongliang on 2018/3/21 17:12.
 * Function(功能):
 *          mock静态方法
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Banana.class})
public class PowerMockitoStaticMethodTest {
    //@PrepareForTest({Banana.class}) 多个使用时 在 类外边写

//    PowerMock必须加注解@PrepareForTest和@RunWith(PowerMockRunner.class)。
// 注解@PrepareForTest里写的是静态方法所在的类。
    @Test
//    @PrepareForTest({Banana.class})
    public void testStaticMethod() {
        PowerMockito.mockStatic(Banana.class); //<-- mock静态类
        Mockito.when(Banana.getColor()).thenReturn("绿色");
        Assert.assertEquals("绿色", Banana.getColor());
    }

    //要更改类的私有static常量 比如修改Banana中的COLOR。
    @Test
//    @PrepareForTest({Banana.class})
     public void testChangeColor() {
        //Internal 内部
        Whitebox.setInternalState(Banana.class, "COLOR", "红色的");
        Assert.assertEquals("红色的", Banana.getColor());
    }




}

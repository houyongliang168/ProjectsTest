package com.example.houyongliang.testdemo.PowerMock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by houyongliang on 2018/3/21 17:25.
 * Function(功能):
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Banana.class})
public class PowerMockitoPrivateMethodTest {
    @Test

    public void testPrivateMethod() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenCallRealMethod();//这句 可以获取私有方法
        PowerMockito.when(mBanana, "flavor").thenReturn("苦苦的");
        Assert.assertEquals("苦苦的黄色的", mBanana.getBananaInfo());
        //验证flavor是否调用了一次
        PowerMockito.verifyPrivate(mBanana).invoke("flavor");
    }

    //跳过私有方法
    @Test
//    @PrepareForTest({Banana.class})
    public void skipPrivateMethod() {
        Banana mBanana = new Banana();
        //跳过flavor方法
        PowerMockito.suppress(PowerMockito.method(Banana.class, "flavor"));
        Assert.assertEquals("null黄色的", mBanana.getBananaInfo());
    }

    //更改父类私有变量
    @Test
//    @PrepareForTest({Banana.class})
    public void testChangeParentPrivate() throws Exception {
        Banana mBanana = new Banana();
        MemberModifier.field(Banana.class, "fruit").set(mBanana, "蔬菜");
        Assert.assertEquals("蔬菜", mBanana.getFruit());
    }

//    mock final方法
    @Test
//    @PrepareForTest({Banana.class})
    public void testFinalMethod() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.isLike()).thenReturn(false);
        Assert.assertFalse(mBanana.isLike());
    }

}

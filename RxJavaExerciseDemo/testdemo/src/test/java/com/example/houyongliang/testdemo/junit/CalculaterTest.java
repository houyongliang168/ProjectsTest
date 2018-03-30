package com.example.houyongliang.testdemo.junit;

import com.example.houyongliang.testdemo.junit.Calculater;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by houyongliang on 2018/3/20 14:01.
 * Function(功能):
 */

//        assertEquals 断言传入的预期值与实际值是相等的
//        assertNotEquals 断言传入的预期值与实际值是不相等的
//        assertArrayEquals 断言传入的预期数组与实际数组是相等的
//        assertNull 断言传入的对象是为空
//        assertNotNull 断言传入的对象是不为空
//        assertTrue 断言条件为真
//        assertFalse 断言条件为假
//        assertSame 断言两个对象引用同一个对象，相当于“==”
//        assertNotSame 断言两个对象引用不同的对象，相当于“!=”
//        assertThat 断言实际值是否满足指定的条件
//        注意：上面的每一个方法，都有对应的重载方法，可以在前面加一个String类型的参数，表示如果断言失败时的提示
public class CalculaterTest {
    Calculater calculater = new Calculater();

    @Test
    public void testAdd() {
        int a = 1;
        int b = 2;

        int result = calculater.add(a, b);

        Assert.assertEquals(result, 4); // 验证result==3，如果不正确，测试不通过

    }

    @Test
    public void testAdd2() {
//        calculater = mock(Calculater.class);
//        calculater.add(1, 2);
//
//        verify(calculater).add(1, 2); // 验证calculater.add(a, b)是否被调用过，且a==1 && b==2
//         测试通过
    }

//    @Test	表示此方法为测试方法
//    @Before	在每个测试方法前执行，可做初始化操作
//    @After	在每个测试方法后执行，可做释放资源操作
//    @Ignore	忽略的测试方法
//    @BeforeClass	在类中所有方法前运行。此注解修饰的方法必须是static void
//    @AfterClass	在类中最后运行。此注解修饰的方法必须是static void
//    @RunWith	指定该测试类使用某个运行器
//    @Parameters	指定测试类的测试数据集合
//    @Rule	重新制定测试类中方法的行为
//    @FixMethodOrder	指定测试类中方法的执行顺序
//    执行顺序：@BeforeClass –> @Before –> @Test –> @After –> @AfterClass

    @Test
    public void testAdd3(){

    }


}

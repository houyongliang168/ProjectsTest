package com.example.houyongliang.testdemo.mockito;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by houyongliang on 2018/3/21 15:56.
 * Function(功能):
 *
 *
 * Mockito框架不支持mock匿名类、final类、static方法、private方法。
 */
//推荐 该方法
public class MockitoRuleTest {
    //MockitoRule方法
//  @Spy 效果与 @Mock 相同
    @Mock //<--使用@Mock注解
            Person mPerson;

    @Rule //<--使用@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull() {
        assertNotNull(mPerson);
    }
//   常用的打桩？？

    //    thenReturn(T value)	设置要返回的值
//    thenThrow(Throwable… throwables)	设置要抛出的异常
//    thenAnswer(Answer<?> answer)	对结果进行拦截
//    doReturn(Object toBeReturned)	提前设置要返回的值
//    doThrow(Throwable… toBeThrown)	提前设置要抛出的异常
//    doAnswer(Answer answer)	提前对结果进行拦截
//    doCallRealMethod()	调用某一个方法的真实实现
//    doNothing()	设置void方法什么也不做
    @Test
    public void testPersonReturn() {
//        当执行什么方法时，然后就返回什么结果
        when(mPerson.getName()).thenReturn("小明");//赋值
        when(mPerson.getSex()).thenThrow(new NullPointerException("···性别不明"));//赋值
        //输出小明
        System.out.println(mPerson.getName());
        //抛出异常
        System.out.println(mPerson.getSex());

    }

    @Test
    public void testPersonReturn2() {
        //以什么结果返回，当执行什么方法时

        // 这个两者的区别就是我们熟悉的while与do while。
        // 这类方法主要是为了应对无法使用thenReturn等方法的场景（比如方法为void），可读性来说thenReturn这类更好。
        doReturn("小心").when(mPerson).getName();//成功
//        doReturn("小心").when(mPerson.getName());//失败
        System.out.println(mPerson.getName());
    }

    @Test
    public void testPersonAnswer() {
        when(mPerson.eat(anyString())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();

                return args[0].toString() + "难吃的";
            }
        });

        System.out.println(mPerson.eat("面"));
    }

    //常用验证方法
    //前面所说的都是状态测试，但是如果不关心返回结果，而是关心方法有否被正确的参数调用过，
    // 这时候就应该使用验证方法了。从概念上讲，就是和状态测试所不同的“行为测试”了。

//    verify(T mock)验证发生的某些行为 。
//
//    方法名	方法描述
//    after(long millis)	在给定的时间后进行验证
//    timeout(long millis)	验证方法执行是否超时
//    atLeast(int minNumberOfInvocations)	至少进行n次验证
//    atMost(int maxNumberOfInvocations)	至多进行n次验证
//    description(String description)	验证失败时输出的内容
//    times(int wantedNumberOfInvocations)	验证调用方法的次数
//    never()	验证交互没有发生,相当于times(0)
//    only()	验证方法只被调用一次，相当于times(1)


    @Test
    public void testPersonVerifyAfter() {
        when(mPerson.getAge()).thenReturn(18);
        //延时1s验证
        System.out.println(mPerson.getAge());
        System.out.println(System.currentTimeMillis());
        verify(mPerson, after(1000)).getAge();
        System.out.println(System.currentTimeMillis());

        verify(mPerson, atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtLeast() {
        mPerson.getAge();
        mPerson.getAge();
        //至少验证2次
        verify(mPerson, atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtMost() {
        mPerson.getAge();
        //至多验证2次
        verify(mPerson, atMost(2)).getAge();
    }

    @Test
    public void testPersonVerifyTimes1() {
        mPerson.getAge();
        mPerson.getAge();
        //验证方法调用2次
        verify(mPerson, times(2)).getAge();
    }

    @Test
    public void testPersonVerifyTimes() {
        mPerson.getAge();
        mPerson.getAge();
        //验证方法在100ms超时前调用2次
        verify(mPerson, timeout(100).times(2)).getAge();
    }


//    方法名	方法描述
//    anyObject()	匹配任何对象
//    any(Class<T> type)	与anyObject()一样
//    any()	与anyObject()一样
//    anyBoolean()	匹配任何boolean和非空Boolean
//    anyByte()	匹配任何byte和非空Byte
//    anyCollection()	匹配任何非空Collection
//    anyDouble()	匹配任何double和非空Double
//    anyFloat()	匹配任何float和非空Float
//    anyInt()	匹配任何int和非空Integer
//    anyList()	匹配任何非空List
//    anyLong()	匹配任何long和非空Long
//    anyMap()	匹配任何非空Map
//    anyString()	匹配任何非空String
//    contains(String substring)	参数包含给定的substring字符串
//    argThat(ArgumentMatcher <T> matcher)	创建自定义的参数匹配模式

    @Test
    public void testPersonAny() {
        when(mPerson.eat(any(String.class))).thenReturn("米饭");
        //或
        //when(mPerson.eat(anyString())).thenReturn("米饭");

        //输出米饭
        System.out.println(mPerson.eat("面条"));

    }


    @Test
    public void testPersonContains() {

        when(mPerson.eat(contains("面"))).thenReturn("面条");
        //输出面条
        System.out.println(mPerson.eat("面"));

    }

    @Test
    public void testPersonArgThat() {

        //自定义输入字符长度为偶数时，输出面条。
        when(mPerson.eat(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return argument.length() % 2 == 0;
            }
        }))).thenReturn("面条");
        //输出面条
        System.out.println(mPerson.eat("1234"));

    }

//    其他方法
//    方法名	方法描述
//    reset(T … mocks)	重置Mock
//    spy(Class<T> classToSpy)	实现调用真实对象的实现
//    inOrder(Object… mocks)	验证执行顺序
//    @InjectMocks注解	自动将模拟对象注入到被测试对象中


}

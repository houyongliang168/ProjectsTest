package com.example.houyongliang.ProxyInstance;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by houyongliang on 2018/2/7.
 *
 * 动态代理测试
 */

public class Test {
    public static void main(String[] args) {
        IUserService userService = (IUserService) Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class[]{IUserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("method = " + method.getName() +" , args = " + Arrays.toString(args));
                return null;
            }
        });
        System.out.println(userService.getClass());
        userService.login("zhy","123");
    }
}

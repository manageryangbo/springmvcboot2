/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProxyTest
 * Author:   martin
 * Date:     2018/5/17 14:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest2 implements InvocationHandler{
    // 目标对象
    private Object target;

    /**
     * 构造方法
     *
     * @param target 目标对象
     */
    public ProxyTest2(Object target) {
        super();
        this.target = target;
    }

    public static void main(String[] args) throws Throwable{
        // 实例化目标对象
        UserService userService = new UserServiceImpl();
        ProxyTest2 proxyTest2 = new ProxyTest2(userService);
        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                userService.getClass().getInterfaces(), proxyTest2);
        userServiceProxy.add();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(target,args);
        System.out.println("after");
        return result;
    }
}

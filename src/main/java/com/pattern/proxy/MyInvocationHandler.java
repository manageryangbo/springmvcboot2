/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MyInvocationHandler
 * Author:   martin
 * Date:     2018/5/17 14:32
 * Description: 动态代理实现自己的InvocationHandler
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    /**
     * 构造方法
     *
     * @param target 目标对象
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }


    /**
     * 执行目标对象的方法
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("-----------------jdk的动态代理 before------------------");

        // 执行目标对象的方法
        Object result = method.invoke(target, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("-------------------jdk的动态代理 after------------------");

        return result;
    }

    /**
     * 获取目标对象的代理对象
     *
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CglibProxyFactory
 * Author:   martin
 * Date:     2019/7/10 15:58
 * Description: Cglib代理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {

    private Object target;//维护一个目标对象
    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    //为目标对象生成代理对象
    public Object getProxyInstance() {
        //工具类
        Enhancer en = new Enhancer();
        //设置父类
        en.setSuperclass(target.getClass());
        //设置回调函数
        en.setCallback(this);
        //创建子类对象代理
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("-----------------Cglib代理 before------------------");
        // 执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        System.out.println("-----------------Cglib代理 after------------------");
        return null;
    }
}

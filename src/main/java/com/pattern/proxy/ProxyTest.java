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

public class ProxyTest {

    public static void main(String[] args) throws Throwable{

        // 实例化目标对象
        UserService userService = new UserServiceImpl();

        // 实例化InvocationHandler
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);

        // 根据目标对象生成代理对象(强制转换Object成代理对象【$Proxy11 extends Proxy  implements UserService 】 )
        UserService proxy = (UserService) invocationHandler.getProxy();

        // 调用代理对象($Proxy11)的方法
        proxy.add();

    }

}

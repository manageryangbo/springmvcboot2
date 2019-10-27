/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   martin
 * Date:     2018/5/17 14:36
 * Description: 静态代理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.proxy;

public class UserProxyServiceImpl implements UserService{

    private UserService target;

    public UserProxyServiceImpl(UserService target) {
        this.target = target;
    }

    /* (non-Javadoc)
         * @see dynamic.proxy.UserService#add()
         */
    @Override
    public void add() {
        System.out.println("--------------------静态代理 before---------------");
        target.add();
        System.out.println("--------------------静态代理 after---------------");

    }

}

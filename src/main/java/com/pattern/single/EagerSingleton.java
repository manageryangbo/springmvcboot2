/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EagerSingleton
 * Author:   martin
 * Date:     2018/5/23 10:40
 * Description: 饿汉单例模式
 *          只有初始化时生成实例，构造方法(私有化)，别人调用不到
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.single;

public class EagerSingleton {

    private static EagerSingleton instance = new EagerSingleton();
    /**
     * 私有默认构造子
     */
    private EagerSingleton(){
        System.out.println( "call private eagerSingleton" );
    }
    /**
     * 静态工厂方法
     */
    public static EagerSingleton getInstance(){
        return instance;
    }

}

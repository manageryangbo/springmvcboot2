/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LazySingleton
 * Author:   martin
 * Date:     2018/5/23 10:51
 * Description: 懒汉单例模式
 *              相对饿汉模式，不用初始化类时就生成对象(时间换空间)
 *              缺点:调用同步实例方法时，其他调用只能等待，好在实现是线程安全的
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.single;

public class LazySingleton {
    private static LazySingleton instance = null;
    /**
     * 私有默认构造子
     */
    private LazySingleton(){}
    /**
     * 静态工厂方法
     */
    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}

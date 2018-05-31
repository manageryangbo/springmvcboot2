/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DoubleCheckSingleton
 * Author:   martin
 * Date:     2018/5/23 11:26
 * Description: 双重检查加锁实现单列
 *              在懒加载的条件下进行优化(不同步方法一整块,只同步实例的实现代码)
 *              同步块的外层和内层都加一个Null的条件判断(除了第一次外,都会走到同步代码，一定程度上节省了时间资源)
 *              实例对象用volatile来修饰:
 *                  对象将不会被本地线程缓存，所有对该变量的读写都是直接操作共享内存，
 *                  从而确保多个线程能正确的处理该变量。
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.single;

public class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton instance = null;
    private DoubleCheckSingleton(){}
    public static DoubleCheckSingleton getInstance(){
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if(instance == null){
            //同步块，线程安全的创建实例
            synchronized (DoubleCheckSingleton.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if(instance == null){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}

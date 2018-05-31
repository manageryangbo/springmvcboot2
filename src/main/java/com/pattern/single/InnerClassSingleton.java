/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: InnerClassSingleton
 * Author:   martin
 * Date:     2018/5/23 14:21
 * Description: 通过内部类和多线程缺省同步锁，实现了延迟加载和线程安全
 *              延迟加载，原理:该内部类的实例与外部类的实例没有绑定关系，
 *                  而且静态成员变量只有被调用到时才会装载，从而实现了(延迟加载)。
 *              静态初始化器，由JVM来保证(线程安全)
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.single;

public class InnerClassSingleton {
    private InnerClassSingleton(){}
    /**
     *    类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例没有绑定关系，
     *    而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance(){
        return SingletonHolder.instance;
    }
}

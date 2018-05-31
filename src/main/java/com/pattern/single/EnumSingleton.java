/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EnumSingleton
 * Author:   martin
 * Date:     2018/5/23 14:29
 * Description: 枚举类是一个特殊的单例模式类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.single;

public enum EnumSingleton {
    /**
     * 定义一个枚举的元素，它就代表了Singleton的一个实例。
     */
    uniqueInstance;

    /**
     * 单例可以有自己的操作
     */
    public void singletonOperation(){
        //功能处理
    }
}

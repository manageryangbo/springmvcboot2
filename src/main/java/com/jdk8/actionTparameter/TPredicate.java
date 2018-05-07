/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TPredicate
 * Author:   martin
 * Date:     2018/4/26 14:52
 * Description: 泛型物品处理接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.actionTparameter;

@FunctionalInterface
public interface TPredicate<T> {
//    boolean judge(T t);

      boolean effect(T t);
}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ConcreteStateA
 * Author:   martin
 * Date:     2018/7/17 10:06
 * Description: 具体状态类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.state;

public class ConcreteStateA implements State{
    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateA handle ：" + sampleParameter);
    }
}

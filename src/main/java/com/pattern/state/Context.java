/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Context
 * Author:   martin
 * Date:     2018/7/17 10:04
 * Description: 环境角色类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.state;

public class Context {

    //持有一个State类型的对象实例
    private State state;

    public void setState(State state) {
        this.state = state;
    }
    /**
     * 用户感兴趣的接口方法
     */
    public void request(String sampleParameter) {
        //转调state来处理
        state.handle(sampleParameter);
    }
}

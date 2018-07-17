/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ClentTest
 * Author:   martin
 * Date:     2018/7/17 10:09
 * Description: 状态模式的功能就是分离状态的行为，通过维护状态的变化，来调用不同状态对应的不同功能。【状态决定行为】
 *             (状态模式跟策略模式类似)
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.state;

public class ClentTest {
    public static void main(String[] args){
        //创建状态
        State state = new ConcreteStateB();
        //创建环境
        Context context = new Context();
        //将状态设置到环境中
        context.setState(state);
        //请求
        context.request("test");
    }
}

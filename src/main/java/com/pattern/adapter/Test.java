/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Test
 * Author:   martin
 * Date:     2018/5/15 16:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.adapter;

public class Test {

    public static void main(String[] args) {
        Target t = new ClassAdapter();
        t.sampleOperation1();  // 达到类适配器的效果(Adaptee类的接口变换成客户端所期待的另一种Target接口)

        Target2 t2 = new ObjectAdapter( new Adaptee() );
        t2.sampleOperation1();// 达到对象适配器的效果(Adaptee类的接口变换成客户端所期待的另一种Target接口)
        t2.sampleOperation2();
    }

}

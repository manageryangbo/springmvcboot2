/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ObjectAdapter
 * Author:   martin
 * Date:     2018/5/15 16:14
 * Description: 对象适配器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.adapter;

public class ObjectAdapter implements Target2{

    Adaptee  adaptee ;

    public ObjectAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    public void sampleOperation1(){
        System.out.println( "Come In ObjectAdapter" );
        this.adaptee.sampleOperation1();
    }

    public void sampleOperation2(){
        //写相关的代码
    }

}

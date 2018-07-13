/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Shanghai
 * Author:   martin
 * Date:     2018/7/13 15:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.observer;

public class Shanghai implements Observer{
    @Override
    public void notify(String tweet) {
        if(tweet!=null && tweet.contains("city")){
            System.out.println("listen news in shanghai:"+tweet);
        }
    }
}

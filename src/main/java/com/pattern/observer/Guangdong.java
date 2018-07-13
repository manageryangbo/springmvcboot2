/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Guangdong
 * Author:   martin
 * Date:     2018/7/13 15:02
 * Description: 广东
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.observer;

public class Guangdong implements Observer{
    @Override
    public void notify(String tweet) {
        if(tweet!=null && tweet.contains("province")){
            System.out.println("listen news in guangdong:"+tweet);
        }
    }
}

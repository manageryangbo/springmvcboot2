/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Beijing
 * Author:   martin
 * Date:     2018/7/13 14:58
 * Description: 北京地区
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.observer;

public class Beijing implements Observer{

    @Override
    public void notify(String tweet) {
        if(tweet!=null && tweet.contains("city")){
            System.out.println("listen news in beijing:"+tweet);
        }
    }
}

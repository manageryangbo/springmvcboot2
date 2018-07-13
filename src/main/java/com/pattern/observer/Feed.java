/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Feed
 * Author:   martin
 * Date:     2018/7/13 15:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject{

    private final List<Observer> observers= new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add( o );
    }

    @Override
    public void notifyObserver(String tweet) {
        this.observers.forEach( o -> o.notify(tweet));
    }
}

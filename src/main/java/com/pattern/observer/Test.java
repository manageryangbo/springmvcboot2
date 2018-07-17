/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Test
 * Author:   martin
 * Date:     2018/7/13 15:08
 * Description:
 *          策略模式也是观察者模式的一种:
 *              将观察者对象注册到主体类中，调用主体类方法时，通过注册的观察者对象调用它的实现方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.observer;

public class Test {
    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver( new Guangdong());
        f.registerObserver( new Beijing());
        f.registerObserver( new Shanghai());
        f.registerObserver( new Observer() {
            @Override
            public void notify(String tweet) {
                if(tweet!=null && tweet.contains("city")){
                    System.out.println("listen news in changsha:"+tweet);
                }
            }
        });
        f.registerObserver( (String tweet)->{
            if(tweet!=null && tweet.contains("city")){
                System.out.println("listen news in shenzhen:"+tweet);
            }
        } );
        f.notifyObserver("china of province or city listen to news!");
        demo();
    }

    private static void demo(){
        System.out.println("demo");
    }
}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestApp
 * Author:   martin
 * Date:     2018/4/25 14:42
 * Description: 测试苹果
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.actionappleparameter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestApp {
    public static void main(String[] args) {
        // 待筛选苹果
        List<App> appList = new ArrayList<App>();
        App app1 = new App();app1.setColor("red");app1.setWeight(125);appList.add(app1);
        App app2 = new App();app2.setColor("green");app2.setWeight(175);appList.add(app2);
        App app3 = new App();app3.setColor("green");app3.setWeight(115);appList.add(app3);
        App app4 = new App();app4.setColor("red"); app4.setWeight(165);appList.add(app4);
        // 标准苹果
        App standardApp = new App();
        standardApp.setColor("red");
        standardApp.setWeight(150);
        // 第二参数是形参 ( new HeavyApplePredicate )
        List<App> resultApp = filter(appList , standardApp , new HeavyApplePredicate());
        // 匿名内部类  new ApplePredicate(){...}
        filter(appList ,standardApp , new ApplePredicate() {
            @Override
            public boolean judge(App app, App toApp) {
                return app.getWeight()<toApp.getWeight();
            }
        });
        // Lambda写法
        List<App> resultAppLambda = filter( appList ,standardApp, ( App app,App standardAppCopy ) ->  !standardAppCopy.getColor().equals( app.getColor() )   );
        System.out.println(resultAppLambda.toString());

    }

    public static List<App> filter(List<App> inventory, App standardApp, ApplePredicate p) {
        List<App> resultApp = new ArrayList<>();
        for (App app : inventory) {
            if (p.judge(app, standardApp)) {
                resultApp.add(app);
            }
        }
        return resultApp;
    }

}

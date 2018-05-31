/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestLambdaProperty
 * Author:   martin
 * Date:     2018/4/28 16:14
 * Description: 测试算法基本属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.lambda;

import com.jdk8.actionappleparameter.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.apache.coyote.http11.Constants.a;

public class TestLambdaProperty {
    public static void main(String[] args) {

        List<String> list= Arrays.asList("a","c","b");
        Comparator<String> c = ( s1,  s2)->s1.compareToIgnoreCase(s2);  //lambda表达式
        list.sort( c );
        Comparator<String> c2 = String::compareToIgnoreCase;  // 方法引用
        list.sort( c2 );

        Supplier<App> supplier = () ->new App();  // Supplier<T> lambda表达式
        App app = supplier.get();

//        Function<App,String> ss = (App a)->a.getColor();
        Supplier<App> s = App::new;   // Supplier<T> 方法引用 构造方法
        s.get();
        Function<Integer,App> f = App::new;  // Supplier<R,T>  方法引用
        f.apply(100);
        BiFunction<String,Integer,App> bf = App::new;  // BiFunction<U,R,T>  方法引用
        bf.apply("green",164);

        Function<String,Integer> ff = Integer::parseInt;
        Function<String,Integer> ff2 = (str)-> Integer.parseInt(str) ;

        Function<App,Integer> ss = (App a)->a.getWeight();
        App app1 =new App(123);
        System.out.println( ss.apply(app1)  );

        // 复合Lambda表达式
        List<App> appList = new ArrayList<App>();
        App app11 = new App();app11.setColor("red");app11.setWeight(125);appList.add(app11);
        App app2 = new App();app2.setColor("green");app2.setWeight(175);appList.add(app2);
        App app3 = new App();app3.setColor("green");app3.setWeight(115);appList.add(app3);
        App app4 = new App();app4.setColor("red"); app4.setWeight(165);appList.add(app4);
        Comparator<App> reversedComparator = Comparator.comparing((App a) -> a.getWeight()).reversed();
        appList.stream().sorted( reversedComparator );
        Function<App,Integer> f3 = (App a)->a.getWeight();
        Predicate<App> p = (App a)->a.getWeight()>150;

    }
}


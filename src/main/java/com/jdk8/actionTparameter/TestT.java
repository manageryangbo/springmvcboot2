/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestT
 * Author:   martin
 * Date:     2018/4/26 14:54
 * Description: 测试泛型类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.actionTparameter;

import com.jdk8.actionappleparameter.App;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class TestT {
    public static void main(String[] args) {

        List<Tomato> tomatoList = new ArrayList<Tomato>();
        Tomato tomato1 = new Tomato();tomato1.setColor("red");tomato1.setWeight(125);tomatoList.add(tomato1);
        Tomato tomato2 = new Tomato();tomato2.setColor("green");tomato2.setWeight(175);tomatoList.add(tomato2);
        Tomato tomato3 = new Tomato();tomato3.setColor("green");tomato3.setWeight(115);tomatoList.add(tomato3);
        Tomato tomato4 = new Tomato();tomato4.setColor("red"); tomato4.setWeight(165);tomatoList.add(tomato4);
        tomatoList.sort( comparing( Tomato::getWeight ) ); // 方法引用
        tomatoList.sort( comparing( Tomato::getWeight ) ); // 方法引用
        tomatoList.sort( (Tomato t1,Tomato t2)->t1.getColor().compareToIgnoreCase( t2.getColor() ) );

        //传统写法(行为性参数匿名类，泛型)
        List<Tomato> resultTomato = filter(tomatoList, new TPredicate() {
//            @Override
//            public boolean judge(Object o) {
//                return "green".equals( ((Tomato)o).getColor() );
//            }
            @Override
            public boolean effect(Object o) {
                return !"green".equals( ((Tomato)o).getColor() );
            }
        });
        // Lambda写法
        List<Tomato> resultTomatoLambda = filter(tomatoList, (TPredicate<Tomato>) (Tomato   standTomato) ->  !"red".equals( standTomato.getColor() ));
        System.out.println(  resultTomato.toString());
        System.out.println(  resultTomatoLambda.toString());
    }
    public static<T> List<T> filter(List<T> inventory, TPredicate<T> tp) {
        List<T> resultApp = new ArrayList<>();
        for (T t : inventory) {
            if (tp.effect(t)) {
                resultApp.add(t);
            }
        }
        return resultApp;
    }

    public static<T> List<T> filter(List<T> inventory, TPredicateCopy<T> tpc) {
        List<T> resultApp = new ArrayList<>();
        for (T t : inventory) {
            if (tpc.effect(t)) {
                resultApp.add(t);
            }
        }
        return resultApp;
    }


}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Test
 * Author:   martin
 * Date:     2018/6/1 14:30
 * Description: 常规练习
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.practise;

import java.util.*;

public class Test {
    public static void main(String[] args) {

        // 数组和集合的(类型和大小)最好同步 (String&&list.size)
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");
        String[] array = new String[list.size()];
        array = list.toArray(array);

        String[] str = new String[] { "a", "b" };
        List asList = Arrays.asList(str);
//        asList.add("c");
//        Arrays.asList 体现的是适配器模式，只是转换接口，asList后台的数据仍是数组
//        运行时抛:UnsupportedOperationException
        str[0] = "gujin";
        System.out.println( asList.get(0) );  // 数组改变值时，转换过来的集合值也会变化

        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {  //反例
            if ("1".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(  a.toString() );
        Iterator<String> it = a.iterator();
        while (it.hasNext()) {  //正例
            String temp = it.next();
            if ("2".equals(temp)) {
                it.remove();
            }
        }
        System.out.println(  a.toString() );


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("first","第一天");
        map.put("second","第二天");
        map.forEach((k,v)->System.out.println("jdk8 【key:" + k + ";value:" + v + "】"));  // jdk8
        Set<Map.Entry<String, Object>> entriesMap = map.entrySet();
        for (Map.Entry<String,Object> entry : entriesMap){
            System.out.println("entry【key:"+entry.getKey()+";value:"+entry.getValue()+"】");
        }

    }
}

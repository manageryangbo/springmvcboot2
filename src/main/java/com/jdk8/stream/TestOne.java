/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestOne
 * Author:   martin
 * Date:     2018/5/2 14:27
 * Description: 第一个测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.stream;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class TestOne {
    public static void main(String[] args) {
        List<Dish> toSortList = new ArrayList<>();
        Dish d1 = new Dish();
        d1.setCalories(200);
        d1.setName("dish2");
        Dish d2 = new Dish();
        d2.setCalories(100);
        d2.setName("dish1");
        Dish d3 = new Dish();
        d3.setCalories(300);
        d3.setName("dish3");
        toSortList.add(d1);
        toSortList.add(d2);
        toSortList.add(d3);
        Collections.sort(toSortList, (new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        }).reversed());
        System.out.println(toSortList.toString());

        List<String> lowCaloricDishName = toSortList.stream().filter(d -> d.getCalories() >= 200).sorted(comparing(Dish::getCalories).reversed()).map(d -> d.getName()).collect(Collectors.toList());
        System.out.println(lowCaloricDishName.toString());

        Function<Dish, String> f = d -> d.getName();
        Function<Dish, String> ff = Dish::getName;

        List<String> title = Arrays.asList("sdk", "sun", "oracle");
//        List<String> title = null;  //NullPointerException
        Stream<String> stream = title.stream();
        stream.forEach(System.out::println);   //Conusmer函数接口的lambda<=>方法引用(str->System.out.println(str) <=>System.out:println
//      stream.forEach( str-> System.out.println(str) ); // 已经消费过的流就会关闭，再消费就抛出异常(IllegalStateException:stream has already been operated upon or closed)

        String[] arrayOfWord = {"Hello", "World"};
        Stream<String> streamArray = Arrays.stream(arrayOfWord).map(str -> str.split("")).flatMap(Arrays::stream).distinct();
        streamArray.forEach(System.out::println);
        // 返回一个流的集合结果
//        List<Stream<String>> resultList = title.stream().map(word->word.split("")).map(Arrays::stream).distinct().collect( Collectors.toList() );

        List<Integer> srqList = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> srqStream = srqList.stream().map(num -> num * num);
        srqStream.forEach(System.out::println);

        List<Integer> num1List = Arrays.asList(1, 2, 3);
        List<Integer> num2List = Arrays.asList(3, 4);
        List<int[]> pairs = num1List.stream().flatMap(i -> num2List.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());

        Optional<String> dish = title.stream().filter(str -> str.indexOf("sdk") > -1).findAny();
        dish.ifPresent(System.out::println);  // 如果存在就打印出来
        System.out.println(dish.orElse("default"));

        List<Integer> numbers = Arrays.asList(1, 5, 2, 4, 6);
        int total = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum = numbers.stream().reduce(0, Integer::sum);
        long count = numbers.stream().count();
        System.out.println(count);

        System.out.println(  StringUtils.isNotBlank("   ") ) ;


    }

}

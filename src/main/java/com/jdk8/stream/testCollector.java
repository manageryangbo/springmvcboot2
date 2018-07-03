/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: testCollector
 * Author:   martin
 * Date:     2018/5/7 16:06
 * Description: 测试收集器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingInt;

public class testCollector {
    public enum CaloricLevel { DIET , NORMAL , FAT }


    public static void main(String[] args) {

        List<String> aList = Arrays.asList("a","b","c","d");
        List<String> bList = Arrays.asList("a","c");
        List<String> notContainsItem = aList.stream().filter(item -> !bList.contains(item)).collect(Collectors.toList());
        System.out.println( notContainsItem.toString() );

        List<Dish> toSortList = new ArrayList<>();
        Dish d1 = new Dish();d1.setCalories(200);d1.setName("dish2");d1.setType( Dish.Type.FISH );d1.setVegetarian(false);
        Dish d2 = new Dish();d2.setCalories(100);d2.setName("dish1");d2.setType( Dish.Type.MEAT);d2.setVegetarian(true);
        Dish d3 = new Dish();d3.setCalories(300);d3.setName("dish3");d3.setType( Dish.Type.OTHER );d3.setVegetarian(true);
        Dish d4 = new Dish();d4.setCalories(300);d4.setName("dish4");d4.setType( Dish.Type.MEAT );d4.setVegetarian(false);
        toSortList.add(d1); toSortList.add(d2); toSortList.add(d3);toSortList.add(d4);
        Long collect = toSortList.stream().filter(d->d.getCalories()>200).collect(Collectors.counting());
        long count = toSortList.stream().filter(d->d.getCalories()>200).count();
        System.out.println(collect);
        System.out.println(count);
        // 查找最大值和最小值
        Comparator<Dish> comparing = Comparator.comparing(Dish::getCalories);
        Optional<Dish> maxCollect = toSortList.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        Dish dish = maxCollect.orElse(null);
        System.out.println(  dish.getCalories() );
        Optional<Dish> maxCollect1 = toSortList.stream().collect(Collectors.reducing((dd1, dd2) -> dd1.getCalories() > dd2.getCalories() ? dd1 : dd2));
        Optional<Dish> maxStream = toSortList.stream().reduce((ddd1, ddd2) -> ddd1.getCalories() > ddd2.getCalories() ? ddd1 : ddd2);
        // 总和
        Integer summingInt = toSortList.stream().collect(summingInt(Dish::getCalories));
        System.out.println( summingInt );

        // 收集器
        IntSummaryStatistics intSummaryStatistics = toSortList.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println( intSummaryStatistics.toString() );

        // 连接字符串
        String joinStr = toSortList.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(  joinStr  );
        String reduceStr = toSortList.stream().map(Dish::getName).reduce("", (a, b) -> a  + b + "," );
        System.out.println( reduceStr );
        String reducing3Str = toSortList.stream().collect(Collectors.reducing("", Dish::getName, (a, b) -> a + b + ","));
        System.out.println(  reducing3Str );
        List<String>  strSortList = Arrays.asList( "a","b","c") ;
        String reducing2Str = strSortList.stream().collect(Collectors.reducing("", (astr, bstr) -> astr + bstr + ","));
        System.out.println(  reducing2Str );

        int intStreamSum = toSortList.stream().mapToInt(Dish::getCalories).sum();
        // Collectors.reducing(参数:BigFunction<T,T,T>,不是BigFunction<B,R,T>) 必须是同类型T
        String collectReducing = toSortList.stream().map(Dish::getName).collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();

        // 分组
        Map<Dish.Type, List<Dish>> dishTypeByGroup = toSortList.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println( "dishTypeByGroup:" + dishTypeByGroup.toString() );
        Map<CaloricLevel, List<Dish>> dishCaloricLevelByGroup = toSortList.stream().collect(Collectors.groupingBy( dishes -> {
            if (dishes.getCalories() >= 300) return CaloricLevel.FAT;
            else if (dishes.getCalories() >= 200) return CaloricLevel.NORMAL;
            else return CaloricLevel.DIET;
        }));

        System.out.println( "dishCaloricLevelByGroup:" +  dishCaloricLevelByGroup.toString() );
        Map<Dish.Type, Optional<Dish>> maxCollectOptionDish = toSortList.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));  // 分组下的最大值
        Map<Dish.Type, Dish> maxCollectDish= toSortList.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        Map<Dish.Type, Set<CaloricLevel>> setDishCaloricLevelByGroup = toSortList.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dishes2 -> {
            if (dishes2.getCalories() >= 300) return CaloricLevel.FAT;
            else if (dishes2.getCalories() >= 200) return CaloricLevel.NORMAL;
            else return CaloricLevel.DIET;
        }, Collectors.toSet())));
        System.out.println(  setDishCaloricLevelByGroup.toString());

        // 分区
        Map<Boolean, List<Dish>> vegetarianMap = toSortList.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println( vegetarianMap.toString());
        List<Dish> notVagetarianMap = toSortList.stream().filter(dishsh -> !dishsh.isVegetarian()).collect(Collectors.toList());
        System.out.println(  notVagetarianMap );


        Integer caloriesBinaryOperatorTTT = toSortList.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b);
        // lambda -> 引用
        Integer caloriesSummingInt = toSortList.stream().collect(summingInt(Dish::getCalories));

        List<Integer> zoneList=new ArrayList<Integer>();
        zoneList.add(5);
        zoneList.add(4);
        zoneList.add(6);
        List<Integer> sortZoneList = zoneList.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList());

    }
}


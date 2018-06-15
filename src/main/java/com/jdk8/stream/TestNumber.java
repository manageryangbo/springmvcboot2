/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestNumber
 * Author:   martin
 * Date:     2018/5/7 11:15
 * Description: 数字流测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestNumber {
    public static void main(String[] args) {
        List<Dish> toSortList = new ArrayList<>();
        Dish d1 = new Dish();d1.setCalories(200);d1.setName("dish2");
        Dish d2 = new Dish();d2.setCalories(100);d2.setName("dish1");
        Dish d3 = new Dish();d3.setCalories(300);d3.setName("dish3");
        toSortList.add(d1); toSortList.add(d2); toSortList.add(d3);

        toSortList.stream().collect(  Collectors.reducing( 0,Dish::getCalories,Integer::sum  ) );
        Optional<Integer> reduceSum = toSortList.stream().mapToInt(Dish::getCalories).boxed().collect(Collectors.reducing( Integer::sum));
        int streamSum = toSortList.stream().mapToInt(Dish::getCalories).sum();
        System.out.println( reduceSum.orElse(0) + " <=> " + streamSum );

        // 数字流
        Comparator<Integer> reversedComparator = Comparator.comparing( Integer::intValue ).reversed();
        List<Integer> integerSortList = toSortList.stream().mapToInt(Dish::getCalories).boxed().sorted( reversedComparator ).collect(Collectors.toList());
        System.out.println( integerSortList.toString() );
        IntStream intStream = toSortList.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
        stream.forEach(System.out::println);
//        int sum2 = intStream.sum(); //intStream流已经被关闭，
        OptionalInt maxOptional = toSortList.stream().mapToInt(Dish::getCalories).max();
        int max = maxOptional.orElse(100);
        boolean b = toSortList.stream().map(Dish::getCalories).noneMatch(i -> 100 % i == 0);

        // 构建流
        Stream<String> stringStream = Stream.of("java 8", "lambdas", "in", "Action"); //值创建流
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        int[] numbers = {2,3,6,8,11,13};
        int sum= Arrays.stream(numbers).sum(); //数组创建流

        long unqiueWords = 0 ;
        try {
            Stream<String> lines = Files.lines(Paths.get("E://data.txt"), Charset.defaultCharset());  //文件创建流
            unqiueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println( unqiueWords );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 拓展
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(20).forEach(t-> System.out.println("{"+t[0]+","+t[1]+"}"));



    }
}

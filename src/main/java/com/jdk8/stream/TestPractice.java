/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestPractice
 * Author:   martin
 * Date:     2018/5/4 16:22
 * Description: 使用流的练习
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.stream;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestPractice {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2011,700),
                new Transaction(mario,2012,800),
                new Transaction(alan,2012,950)
        );

        List<Transaction> transactionList = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        List<String> cityList = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        List<Trader> traderNameList = transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity() == "Cambridge").distinct().sorted(Comparator.comparing( Trader::getName )).collect(Collectors.toList());
        String  traderStr = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("",(a,b)->a+b);
        boolean isContainMilan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity() == "Milan");
        transactions.stream().filter(transaction -> transaction.getTrader().getCity()=="Cambridge").map(transaction -> transaction.getValue()).forEach(System.out::println);
        Integer maxValue = transactions.stream().map(Transaction::getValue).reduce(0, Integer::max);
        Optional<Transaction> minTransaction = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        Optional<Transaction> minTransactionAgain = transactions.stream().min(Comparator.comparing(Transaction::getValue));

        List<Goods> list1 = Arrays.asList(new Goods(100,"A"),new Goods(100,"B"),new Goods(100,"C"),new Goods(200,"A"),new Goods(200,"D"),new Goods(300,"D"),new Goods(300,"F"),new Goods(300,"G"),new Goods(300,"J"));
        List<Goods> list2 = Arrays.asList(new Goods(100,"AA"),new Goods(100,"BB"),new Goods(100,"CC"),new Goods(200,"AA"),new Goods(200,"DD"),new Goods(300,"DD"),new Goods(300,"FF"),new Goods(300,"GG"),new Goods(300,"JJ"));
        List<Goods> list3 = Arrays.asList(new Goods(100,"AAA"),new Goods(100,"BBB"),new Goods(100,"CCC"),new Goods(200,"AAA"),new Goods(200,"DDD"),new Goods(300,"DDD"),new Goods(300,"FFF"),new Goods(300,"GGG"),new Goods(300,"JJJ"));

        Map<Integer, List<Goods>> groupMap = list1.stream().collect(Collectors.groupingBy(Goods::getPrice));
        groupMap.forEach((key,goodsList)->{
            System.out.println( key );
            goodsList.forEach( (goods)-> System.out.println( goods.getPrice()+ "=>"+goods.getName()+";") );
        });
    }
}

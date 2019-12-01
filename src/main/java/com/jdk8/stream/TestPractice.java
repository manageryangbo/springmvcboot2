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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        for (Transaction transaction:
                transactionList) {
            System.out.println( "==============" + transaction.getValue() );
        }
        List<String> cityList = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        List<Trader> traderNameList = transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity() == "Cambridge").distinct().sorted(Comparator.comparing( Trader::getName )).collect(Collectors.toList());
        String  traderStr = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("",(a,b)->a+b);
        boolean isContainMilan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity() == "Milan");
        transactions.stream().filter(transaction -> transaction.getTrader().getCity()=="Cambridge").map(transaction -> transaction.getValue()).forEach(System.out::println);
        Integer maxValue = transactions.stream().map(Transaction::getValue).reduce(0, Integer::max);
        Optional<Transaction> minTransaction = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        Optional<Transaction> minTransactionAgain = transactions.stream().min(Comparator.comparing(Transaction::getValue));



    }
}

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: StrategyTest
 * Author:   martin
 * Date:     2019/7/11 10:38
 * Description:
 *          策略模式:
 *            意图：定义一系列的算法,把它们一个个封装起来, 并且使它们可相互替换。
 *            主要解决：  在有多种算法相似的情况下，使用 if...else 所带来的复杂和难以维护。
 *
 *           1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。
 *           2、一个系统需要动态地在几种算法中选择一种。
 *           3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.strategy;

import java.math.BigDecimal;

public class StrategyTest {

    public static void main(String[] args) {
        ContextStrategy contextStrategy = new ContextStrategy(2);
        contextStrategy.calGoodsPrice( new BigDecimal( 2.5 ));
    }
}

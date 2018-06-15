/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: testParallel
 * Author:   martin
 * Date:     2018/5/14 15:18
 * Description: 并行流测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.parallel;

import com.jdk8.actionappleparameter.App;

import java.util.*;
import java.util.stream.Collectors;

public class testParallel {
    public static void main(String[] args) {

        System.out.println(  "iterative sum done in : " + ParallelUtil.measureSumPerf(ParallelUtil::iterativeSum,10000000L)+" msecs"  );
        System.out.println("=====================");
        System.out.println(  "sequential sum done in : " + ParallelUtil.measureSumPerf(ParallelUtil::sequentialSum,10000000L)+" msecs"  );
        System.out.println("=====================");
        System.out.println(  "parallel sum done in : " + ParallelUtil.measureSumPerf(ParallelUtil::parallelSum,10000000L)+" msecs"  );
        System.out.println("=====================");
        System.out.println(  "parallel rangedclosed sum done in : " + ParallelUtil.measureSumPerf(ParallelUtil::parallelRangedSum,10000000L)+" msecs"  );
        /**
         * 总结:
         * 1 iterative生成的装箱对象，需拆箱成数字才能求和
         * 2 iterative很难分成多个独立块来并行执行(每个函数都要依赖前一次的结果)
         * 3 rangedClosed(1,n)解决了拆箱和不能分块的问题
         */

        Long count = 10000L ;
        TPredicate<String> tPredicateInner = new TPredicate<String>() {
            @Override
            public boolean effect(String s) {
                Long count = 1000000L ;
                System.out.println( "inner count:" + count );
                System.out.println( ParallelUtil.measureSumPerf(ParallelUtil::parallelSum, count) );
                return s.contains("s");
            }
        };
        tPredicateInner.effect("ssss");
        TPredicate<String> tPredicateLambda = (String s) -> {

            System.out.println( "lambda count:" + count );
            System.out.println( ParallelUtil.measureSumPerf(ParallelUtil::parallelSum, count) );
            return s.contains("d");
        };
        tPredicateLambda.effect("ssss");
        /**
         * 总结:
         *      匿名内部类可以重新定义变量，Lambda表达式则不行
         *      匿名内部类不重新定义变量时使用全局的值，否则使用内部类的值
         */


    }
}

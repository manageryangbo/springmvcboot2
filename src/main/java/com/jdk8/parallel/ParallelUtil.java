/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ParallelUtil
 * Author:   martin
 * Date:     2018/5/14 15:20
 * Description: 并行帮助类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelUtil {

    /**
     * 原始迭代求和
     * @param n
     * @return 1+2+...+n
     */
    public static long iterativeSum(long n){
        long result = 0 ;
        for (long i = 1L ; i<=n ; i++){
            result += i ;
        }
        return result;
    }

    /**
     * 求前n项和 单线程
     * @param n
     * @return 1+2+...+n
     */
    public static long sequentialSum(long n){
        return Stream.iterate(1L,i->i+1).limit(n).reduce(0L,Long::sum);
    }

    /**
     * 求前n项和 多线程 并行 (序列流)
     * @param n
     * @return 1+2+...+n
     */
    public static long parallelSum(long n){
        return Stream.iterate(1L,i->i+1).limit(n).parallel().reduce(0L,Long::sum);
    }

    /**
     * 求前n项和 多线程 并行 (并列流)
     * @param n
     * @return 1+2+...+n
     */
    public static long parallelRangedSum(long n){
        return LongStream.rangeClosed(1, n).parallel().reduce(0L,Long::sum);
    }

    /**
     * 求前n项和 接收多种流处理方式( 原始迭代，序列流，并列流 )
     *
     */
    public static  long measureSumPerf(Function<Long,Long> adder , Long n ){
        long fastest = Long.MAX_VALUE;
        for (int i = 0 ; i<10; i++){
            long start = System.nanoTime();
            long sum = adder.apply( n );
            long duration = (System.nanoTime()-start)/ 1000000;
            //System.out.println(  "Result:"+sum );
            if ( duration<fastest) fastest = duration;
        }
        return fastest ;
    }

}

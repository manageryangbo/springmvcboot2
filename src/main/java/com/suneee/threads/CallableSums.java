/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CallableSums
 * Author:   martin
 * Date:     2018/5/29 14:05
 * Description: Callable/Future的线程池
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableSums {
    static class CallableSum implements Callable<Long> {
        private final long from;
        private final long to;

        CallableSum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() throws Exception {
            long acc = 0;
            for (long i = from; i <= to; i++) {
                acc = acc + i;
            }
            System.out.println(Thread.currentThread().getName()+" sleep ......");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " : " + acc);
            return acc;
        }
    }

    public static void main(String[] args) throws Exception {
//        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Long>> results = executor.invokeAll(Arrays.asList(
                new CallableSum(0, 10), new CallableSum(0, 1_000), new CallableSum(0, 1_000_000), new CallableSum(0, 1_000_100)
        ));
        executor.shutdown();

        for (Future<Long> result : results) {
            System.out.print("for .... ");
            System.out.println(result.get());
        }

    }
}

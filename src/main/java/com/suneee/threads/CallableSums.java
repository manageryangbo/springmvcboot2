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
import java.util.concurrent.*;

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
            if (acc>100000){
                throw new Exception("Meet error in task." + Thread.currentThread().getName());
            }
            System.out.println(Thread.currentThread().getName()+" sleep ......");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " : " + acc);
            return acc;
        }
    }

    public static void main(String[] args) throws Exception {
//        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<?> submitRunnable = executorService.submit(new Runnable() {  // executor.execute 不会返回任务Future<?>的结果
//            @Override
//            public void run() {     System.out.println("=================submit");  }
//        });
//        submitRunnable.get(); // 返回null对象
//        Future<Long> submit = executorService.submit(new CallableSum(0, 10));
        List<Future<Long>> results = executorService.invokeAll(Arrays.asList(
                new CallableSum(0, 10), new CallableSum(0, 1_000), new CallableSum(0, 1_000_000), new CallableSum(0, 1_000_100)
        ));
        System.out.println( "执行shutdown之前..."  );
        /**
         * shutdown()    只是关闭了提交通道，用submit()是无效的；而内部该怎么跑还是怎么跑，跑完再停。
         * shutdownNow() 能立即停止线程池，正在跑的和正在等待的任务都停下了。并得到未执行任务列表。【通过Thread.interrupt()中断】
         * awaitTermination() 是阻塞的, 调用后，可以继续提交新任务。
         */
        executorService.shutdown();
        for (Future<Long> result : results) {
            try {
                System.out.println(result.get()); // 打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                executorService.shutdownNow();
                e.printStackTrace();
                return;
            }
        }

    }
}

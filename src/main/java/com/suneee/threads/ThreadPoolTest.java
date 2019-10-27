/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ThreadPoolTest
 * Author:   martin
 * Date:     2018/9/6 15:27
 * Description:
 *          corePoolSize 核心池大小
 *          maximumPoolSize  线程池允许最大线程数量
 *          BlockingQueue(ArrayBlockingQueue/LinkBlockingQueue/synchronousQueue)  任务缓存队列
 *          拒绝策略:
 *             1:AbortPolicy 默认的拒绝策略,直接抛出异常
 *             2:DiscardPolicy 会让被线程池拒绝的任务直接抛弃，不会抛异常也不会执行。
 *             3:DiscardOldestPolicy当任务被拒绝添加时，会抛弃任务队列中最旧的任务也就是最先加入队列的，再把这个新任务添加进去。
 *             4:CallerRunsPolicy在任务被拒绝添加后，会调用当前线程池的所在的线程去执行被拒绝的任务。
 *             5:自定义策略
 *
 *
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.concurrent.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

        public static void main(String[] args) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(5));
            for(int i=0;i<17;i++){
                MyTask myTask = new MyTask(i);
//                executor.execute(myTask);
                Future<Integer> future = executor.submit(myTask,i);
                System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                        executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());
            }
            executor.shutdown();
        }
    }

    class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }
}

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: AtomicCounter
 * Author:   martin
 * Date:     2019/5/20 17:51
 * Description: 原子型数据类型类AtomicInteger/AtomicBoolean/AtomicLong等
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.volatiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    // 使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
    public void increment() {
        //获取当前的值并自增
        count.incrementAndGet();
    }
    /**
     * 获取当前的值
     * @return
     */
    public int getCount() {
        return count.get();
    }
    //递减
    public void deIncrement(){
        count.decrementAndGet();
    }


    /**
     * 这里模拟一个递增的任务，递增目标为50000
     */
    public static void main(String[] args) throws InterruptedException {
        final AtomicCounter counter = new AtomicCounter();
        int workCount = 50000;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < workCount; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    counter.increment();
                }
            };
            executor.execute(runnable);
        }
        // 关闭启动线程，执行未完成的任务
        /**
         *  shutdown调用后，不可以再submit新的task，已经submit的将继续执行。
         *  shutdownNow试图停止当前正执行的task，并返回尚未执行的task的list
         */
        executor.shutdown();
        // 等待所有线程完成任务，完成后才继续执行下一步
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("执行结果：count=" + counter.getCount());
    }
}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CountDownLatchDemo
 * Author:   martin
 * Date:     2018/8/30 15:08
 * Description:
 *              CountDownLatch 【共享锁】
 *              实现所有线程等待某个事件发生才会执行
 *              mainThread.join(aThread); 执行后面的操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinTask;

public class CountDownLatchDemo {
    /**
     * 模拟爸爸去公司
     */
    public static void fatherToCompany() {
        System.out.println("爸爸步行去公司。");
    }

    /**
     * 模拟妈妈挤公交去商场
     */
    public static void motherToShopping() {
        System.out.println("妈妈挤公交去商场。");
    }

    /**
     * 模拟我乘地铁回家
     */
    public static void meToHome() {
        System.out.println("我乘地铁回家。");
    }

    /**
     * 一家人吃饭
     */
    public static void togetherToEat() {
        System.out.println("一家人开始吃饭,再去干各自的事");
    }

    /**
     * 一家人唱歌
     */
    public static void togetherToKTV() {
        System.out.println("一家人开始唱歌,再去各自的事");
    }


    private static CountDownLatch latch = new CountDownLatch(2);  // (countDown调用次数,await才继续执行)

    public static void main(String[] args) throws InterruptedException {

        new Thread() {
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fatherToCompany();
            };
        }.start();
        new Thread() {
            public void run() {

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                motherToShopping();
            };
        }.start();
        new Thread() {
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                meToHome();
            };
        }.start();
        new Thread() {
            public void run() {
                togetherToEat();
                try {
                    System.out.println("吃饭进行中......");
                    Thread.sleep(2000);
                    System.out.println("吃饭完成......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            };
        }.start();

        new Thread() {
            public void run() {
                togetherToKTV();
                try {
                    System.out.println("KTV进行中......");
                    Thread.sleep(5000);
                    System.out.println("KTV完成......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            };
        }.start();
        System.out.println("=======结束========");
    }
}

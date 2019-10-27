/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CyclicBarrierDemo
 * Author:   martin
 * Date:     2018/9/7 14:36
 * Description: 循环屏障 可以模拟并发操作 【共享锁】
 *              1） CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
                        CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
                        而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
                    另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private final static int THREAD_WAIT_NUM = 7;

    public static void main(String[] args) {
        CyclicBarrier  cyclicBarrierMaster = new CyclicBarrier(THREAD_WAIT_NUM ,new Runnable(){
            @Override
            public void run() {
                System.out.println("七法师都到齐,出发集七龙珠......");
                callbackDragon();
                System.out.println("七法师事件都弄完了");
            }
        });

        for(int i = 1 ; i <= THREAD_WAIT_NUM ; i++){
            int index = i;
            new Thread(()->{
                try {

                    System.out.println("法师 "+ index + "达到...");
                    cyclicBarrierMaster.await();
                    System.out.println("法师 "+index+"Await 后面事件...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("主线程执行完毕");
    }

    private static void callbackDragon(){
        CyclicBarrier  cyclicBarrierDragonBall = new CyclicBarrier(THREAD_WAIT_NUM ,new Runnable(){
            @Override
            public void run() {  //最后一个await触发了这个方法
                try {
                    System.out.println("七龙珠都集齐,召唤神龙......");
                    Thread.sleep(1000);
                    System.out.println("召唤成功!b（￣▽￣）d　");
                    System.out.println("龙珠线程:" + Thread.currentThread().getName() + ".start()执行了cyclicBarrierDragonBall.run()......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        for(int i = 1 ; i <= THREAD_WAIT_NUM ; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    System.out.println("龙珠 "+ Thread.currentThread().getName() + "; i=" +index + "找到");
                    cyclicBarrierDragonBall.await();
                    System.out.println("龙珠 "+index+" Await 后面事件...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}

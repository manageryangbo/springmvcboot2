/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CyclicBarrierPractice
 * Author:   martin
 * Date:     2019/6/3 11:40
 * Description: CyclicBarrier练习
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierPractice {

    private final static int CYCLIC_BARRIER_MASTER_NUM = 4 ;  // 法师数量

    private final static int CYCLIC_BARRIER_BALL_NUM = 7 ;  // 龙珠数量

    public static void main(String[] args) {

        doMaster();
        System.out.println("主线程执行完毕");

    }

    /**
     * 调用法师的动作
     */
    private static void doMaster(){
        CyclicBarrier cyclicBarrierMaster = new CyclicBarrier(CYCLIC_BARRIER_MASTER_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("法师线程:"+ Thread.currentThread().getName() + " 执行cyclicBarrierMaster.run().....");
                System.out.println( CYCLIC_BARRIER_MASTER_NUM+"个法师都到齐,准备出发集七龙珠......" );
                try {
                    System.out.println( "最后法师休息一会......" );
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                doBall();
            }
        });

        for (int i = 1 ; i <= CYCLIC_BARRIER_MASTER_NUM ; i++){
            int index = i ;
            new Thread( () ->{
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+";法师"+index+"到达!");
                    cyclicBarrierMaster.await();
                    System.out.println( Thread.currentThread().getName()+"执行的法师，顺带完成其他任务!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }

    /**
     * 调用龙珠的动作
     */
    private static void doBall(){
        CyclicBarrier cyclicBarrierBall = new CyclicBarrier(CYCLIC_BARRIER_BALL_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("线程:"+ Thread.currentThread().getName() + "执行龙珠 cyclicBarrierBall.run().....");
                System.out.println("龙珠集结齐全。。。");
                System.out.println("龙珠线程"+Thread.currentThread().getName()+"合成"+CYCLIC_BARRIER_BALL_NUM+"龙珠！召唤神龙!b（￣▽￣）d　");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i = 1 ; i <= CYCLIC_BARRIER_BALL_NUM ; i++){
            int index = i ;
            new Thread( () ->{
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+";龙珠"+index+"已经被找到!");
                    System.out.println("收到的龙珠"+index+"贮备能量中。。。");
                    cyclicBarrierBall.await(); // 注意:当await次数够后，必须把cyclicBarrierBall.run执行完后，才执行下一步操作
                    System.out.println("看看龙珠"+index+"cyclicBarrierBall.await后的故事");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}

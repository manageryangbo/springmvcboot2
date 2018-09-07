/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LearnCompleteFuture
 * Author:   martin
 * Date:     2018/7/4 13:45
 * Description: CompletableFuture练习类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LearnCompleteFuture {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        useFuture();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("=======================");
//        useCompletableFuture();
    }

    private static void useFuture() throws InterruptedException, ExecutionException {
        System.out.println("Future");
        ExecutorService exector = Executors.newFixedThreadPool(3);
        Future<Void> futureA = exector.submit(() -> work("A1"));
        Future<Void> futureB = exector.submit(() -> work("B1"));
        System.out.println("分开....");
        while (true) {
//            try {
//                futureA.get(1, TimeUnit.SECONDS);
//                break;
//            } catch (TimeoutException e) {
//            }
            try {
                futureB.get(1, TimeUnit.SECONDS);
                break;
            } catch (TimeoutException e) {
            }
        }
        exector.submit(() -> work("C1")).get();
        System.out.println("再分");
        exector.shutdown();
    }

    private static void useCompletableFuture() throws InterruptedException, ExecutionException {
        System.out.println("CompletableFuture");
        CompletableFuture<Void> futureAA = CompletableFuture.runAsync(() -> work("A2"));
        CompletableFuture<Void> futureBB = CompletableFuture.runAsync(() -> work("B2"));
        futureBB.runAfterEither(futureAA, () -> work("C2")).get();
    }

    public static Void work(String name) {
        System.out.println(name + " starts at " + LocalTime.now());
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
        }
        System.out.println(name + " ends at " + LocalTime.now());
        return null;
    }
}

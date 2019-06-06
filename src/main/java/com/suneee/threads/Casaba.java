/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Casaba
 * Author:   martin
 * Date:     2018/8/29 17:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Casaba {
        private static AtomicInteger atomicInt = new AtomicInteger(100);
        private static AtomicStampedReference<Integer> atomicStampedRef =
                new AtomicStampedReference<Integer>(100, 0);

        public static void main(String[] args) throws InterruptedException {
            Thread intT1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    atomicInt.compareAndSet(100, 101);
                    atomicInt.compareAndSet(101, 100);
                }
            });

            Thread intT2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    boolean c3 = atomicInt.compareAndSet(100, 101);
                    System.out.println(c3);        //true 表示被intT1线程改动的对象【100=>101=>100】，还是能正常修改，导致ABA【本应该修改失败false】
                }
            });

            intT1.start();
            intT2.start();
            intT1.join();
            intT2.join();

            Thread refT1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    atomicStampedRef.compareAndSet(100, 101,
                            atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+1);
                    atomicStampedRef.compareAndSet(101, 100,
                            atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+1);
                }
            });

            Thread refT2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    int stamp = atomicStampedRef.getStamp();
                    System.out.println("before sleep : stamp value= " + stamp);    // stamp = 0
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("after sleep : stamp value= " + atomicStampedRef.getStamp());//stamp = 1
                    boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp+1);
                    System.out.println(c3);        //false【AtomicStampedReference解决了CAS导致的ABA问题】
                }
            });

            refT1.start();
            refT2.start();
        }

}

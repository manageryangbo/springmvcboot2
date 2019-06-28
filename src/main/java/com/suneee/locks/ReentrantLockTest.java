/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ReentrantLockTest
 * Author:   martin
 * Date:     2019/6/3 17:13
 * Description:
 *         ReentrantLock触发死锁案例
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.locks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new ThreadDemo(lock1, lock2));//该线程先获取锁1,再获取锁2
        Thread thread1 = new Thread(new ThreadDemo(lock2, lock1));//该线程先获取锁2,再获取锁1
        thread.start();
        thread1.start();
        thread.interrupt();//是第一个线程中断

//        ArrayBlockingQueue
//        LinkedBlockingQueue


    }

    static class ThreadDemo implements Runnable {
        Lock firstLock;
        Lock secondLock;
        public ThreadDemo(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }
        @Override
        public void run() {
            try {
                firstLock.lockInterruptibly(); //供了一个可以响应中断的获取锁的方法，该方法可以用来解决死锁问题。
                TimeUnit.MILLISECONDS.sleep(10);//更好的触发死锁【为了让第一个线程拿第一把锁，第二个线程拿到第二把锁】
                secondLock.lockInterruptibly();

//                firstLock.lock();
//                TimeUnit.MILLISECONDS.sleep(100);//更好的触发死锁
//                secondLock.lock();

//                解决死锁 且正常结束
//                while(!lock1.tryLock()){
//                    System.out.println( Thread.currentThread().getName() + "获取lock1失败循环" );
//                    TimeUnit.MILLISECONDS.sleep(10);
//                }
//                TimeUnit.MILLISECONDS.sleep(100);
//                while(!lock2.tryLock()){
//                    lock1.unlock();
//                    System.out.println( Thread.currentThread().getName() + "获取lock2失败" );
//                    TimeUnit.MILLISECONDS.sleep(10);
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName()+"finally first unlock begin!");
                firstLock.unlock();
                System.out.println(Thread.currentThread().getName()+"finally second unlock begin!");
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName()+"正常结束!");
            }
        }
    }
}

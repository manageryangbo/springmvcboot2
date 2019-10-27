/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ConditionTest
 * Author:   martin
 * Date:     2019/6/18 14:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static void main(String[] args) throws InterruptedException {

        lock.lock();
        new Thread(new SignalThread()).start();
        System.out.println("1主线程等一秒");
        Thread.sleep(1000);
        System.out.println("3主线程等待通知");
        try {
            System.out.println("4-1主线程await开始释放了锁");
            condition.await(); // 进入等待队列，等待别的线程通知重新获取锁。【这时他已经释放上面获取的锁lock.lock()】
        } finally {
            lock.unlock();
        }
        System.out.println("6主线程恢复运行");
    }
    static class SignalThread implements Runnable {

        @Override
        public void run() {
            System.out.println("2子线程锁定前");
            lock.lock();
            System.out.println("4-2子线程拿到锁定");
            try {
                condition.signal();
                System.out.println("5子线程通知主线程");
            } finally {
                lock.unlock();
            }
        }
    }
}

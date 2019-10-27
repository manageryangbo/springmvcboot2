/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ReentrantLockTestCopy
 * Author:   martin
 * Date:     2019/6/18 11:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTestCopy {
    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<5;i++){
            new Thread(new ThreadDemo(i)).start();
        }

    }

    static class ThreadDemo implements Runnable {
        Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override

        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<2;i++){
                System.out.println("进来线程："+id);
                // 当某个线程获取到锁了以后，如果不释放，其他线程lock.lock()就获取不到锁，就无法往下执行
                lock.lock();
                System.out.println("获得锁的线程："+id);
//                lock.unlock();
            }
        }
    }
}

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyBlockingQueue
 * Author:   martin
 * Date:     2019/5/31 14:36
 * Description:
 *          ReentrantLock中使用Condition实现简单的阻塞队列
 *          注意: Condition.await和signal/signalAll的使用必须先ReentrantLock.lock
 *           Condition.await()执行时，表示当前执行的线程i进入等待队列[释放了当前锁state=0，这样别的线程可以加锁]，不会往下执行了，
 *           而signal是用来唤醒await进入同步队列可能继续往下执行
 *
 *           这里体现ReentrantLock的可重入性【同一个ReentrantLock对象能被多次锁定】
 *                                  独立性【有且只有一个线程能获取到锁.即锁成功】
 *                   锁的特性: 就是不同的线程不能同一把锁即lock.lock()成功
 *
 *           在调用await()方法前线程必须获得重入锁，调用await()方法后线程会释放当前占用的锁。
 *           同理在调用signal()方法时当前线程也必须获得相应重入锁，
 *           调用signal()方法后系统会从condition.await()等待队列中唤醒一个线程。
 *           当线程被唤醒后，它就会尝试重新获得与之绑定的重入锁，一旦获取成功将继续执行。
 *           所以调用signal()方法后一定要释放当前占用的锁，这样被唤醒的线程才能有获得锁的机会，才能继续执行
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.locks;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class MyBlockingQueue<E> {
    int size;//阻塞队列最大容量

    ReentrantLock lock = new ReentrantLock();

    LinkedList<E> list=new LinkedList<>();//队列底层实现

    Condition notFull = lock.newCondition();//队列满时的等待条件
    Condition notEmpty = lock.newCondition();//队列空时的等待条件

    public MyBlockingQueue(int size) {
        this.size = size;
    }

    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        System.out.println( "enqueue lock=>" +Thread.currentThread().getName() );
        try {
            System.out.println( "enqueue  "+Thread.currentThread().getName() );
            while (list.size() ==size) {//队列已满,在notFull条件上等待
                System.out.println( "notFull.awaiting"+e );
                notFull.await();  //调用await()方法后当前线程会释放当前占用的锁, 不会往下执行了
                System.out.println( "notFull.awaited"+e );
            }
            list.add(e);//入队:加入链表末尾
            System.out.println("入队：" +e);
            notEmpty.signal(); //通知在notEmpty条件上等待的线程=>notEmpty.await()继续往下执行
        } finally {
            lock.unlock();
            System.out.println( "enqueue unlock=>"+Thread.currentThread().getName()  );
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        System.out.println( "dequeue lock=>" +Thread.currentThread().getName()  );
        try {
            System.out.println( "dequeue  "+Thread.currentThread().getName() );
            while (list.size() == 0) {   //队列为空,在notEmpty条件上等待
                notEmpty.await();
            }
            e = list.removeFirst();//出队:移除链表首元素
            System.out.println("出队："+e);
            notFull.signal();//通知在notFull条件上等待的线程=>notFull.await()继续往下执行
            return e;
        } finally {
            lock.unlock();
            System.out.println( "dequeue unlock=>" +Thread.currentThread().getName()  );
        }
    }

    public void testRreentrantLockFuture() {  // 测试ReentrantLock锁的特性
        try {
            System.out.println(Thread.currentThread().getName() + "come in 先睡一会。。。");
            Thread.sleep(1000);
            if (lock.tryLock()) {
                System.out.println(Thread.currentThread().getName() + "获取锁成功");
                System.out.println(Thread.currentThread().getName() + "释放了锁中");
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁完");
            }else{
                System.out.println(Thread.currentThread().getName() + "获取锁失败");
            }
        }  catch(InterruptedException e){
            e.printStackTrace();
        } finally {
            System.out.println( "线程执行"+Thread.currentThread().getName()+"执行完！" );
        }
    }

}

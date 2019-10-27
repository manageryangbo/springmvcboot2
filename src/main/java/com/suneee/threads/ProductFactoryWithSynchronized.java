/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductFactoryWithSynchronized
 * Author:   martin
 * Date:     2018/3/30 10:18
 * Description: 工厂类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

public class ProductFactoryWithSynchronized {

    private List<String> products;
    private int capacity = 0;
    public ProductFactoryWithSynchronized(int capacity) {
        this.capacity = capacity;
        products = new LinkedList<>();
    }
    // 生产产品
    public synchronized void produce(String product) {
        while (capacity == products.size()) {
            try {
                //打日志的目的是更好的观察消费者和生产者状态
                System.out.println("警告：线程("+Thread.currentThread().getName() + ")准备生产产品，但产品池已满");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products.add(product);
        System.out.println("线程("+Thread.currentThread().getName() + ")生产了一件产品:" + product+";当前剩余商品"+products.size()+"个");
        notify();
    }

    // 消费产品
    public synchronized String consume() {
        while (products.size()==0) {
            try {
                //打日志的目的是更好的观察消费者和生产者状态
                System.out.println("警告：线程("+Thread.currentThread().getName() + ")准备消费产品，但当前没有产品");
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String product = products.remove(0) ;
        System.out.println("线程("+Thread.currentThread().getName() + ")消费了一件产品:" + product+";当前剩余商品"+products.size()+"个");
        notify();  //随机唤起一个线程
        return product;
    }


}

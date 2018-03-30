/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProducerConsumerWithSynchronizedDemo
 * Author:   martin
 * Date:     2018/3/30 10:48
 * Description: 多线程测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

public class ProducerConsumerWithSynchronizedDemo {
    public static void main(String[] args) {
        ProductFactoryWithSynchronized productFactory = new ProductFactoryWithSynchronized(10);
        new Thread(new ProducerWithSynchronized(productFactory),"1号生产者").start();
        new Thread(new ConsumerWithSynchronized(productFactory),"1号消费者").start();
        new Thread(new ConsumerWithSynchronized(productFactory),"2号消费者").start();
    }
}

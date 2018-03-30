/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ConsumerWithSynchronized
 * Author:   martin
 * Date:     2018/3/30 10:47
 * Description: 消费者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

public class ConsumerWithSynchronized implements Runnable{
    private ProductFactoryWithSynchronized productFactory;
    public ConsumerWithSynchronized(ProductFactoryWithSynchronized productFactory) {
        this.productFactory = productFactory;
    }
    public void run() {
        while (true) {
            productFactory.consume();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProducerWithSynchronized
 * Author:   martin
 * Date:     2018/3/30 10:44
 * Description: 生产者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

public class ProducerWithSynchronized implements Runnable{
    private ProductFactoryWithSynchronized productFactory;
    public ProducerWithSynchronized(ProductFactoryWithSynchronized productFactory) {
        this.productFactory = productFactory;
    }
    public void run() {
        int i = 0 ;
        while (true) {
            productFactory.produce(String.valueOf(i));
            i++;
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

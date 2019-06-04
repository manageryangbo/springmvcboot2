/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test
 * Author:   martin
 * Date:     2019/5/31 14:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.locks;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(3);
        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.enqueue(data);
                    } catch (InterruptedException e) {

                    }
                }
            }).start();
        }
        for(int i=0;i<10;i++){
            Thread.sleep(200);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Integer data = queue.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(3000); // 用于隔断两部分测试

        for(int i=0;i<50;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.testRreentrantLockFuture();
                }
            }).start();
        }

    }
}

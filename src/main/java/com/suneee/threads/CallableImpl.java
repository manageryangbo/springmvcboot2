/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CallableImpl
 * Author:   martin
 * Date:     2018/5/29 11:35
 * Description: 1 相比直接实现Runnable，多了返回结果和抛出异常
 *              2 Callable接口支持返回执行结果，此时需要调用FutureTask.get()方法实现，
 *              此方法会阻塞主线程直到获取‘将来’结果；当不调用此方法时，主线程不会阻塞！
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableImpl implements Callable{

    public CallableImpl(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    private String acceptStr;

    @Override
    public String call() throws Exception {
        // 任务阻塞 5 秒
        System.out.println( "call coming...."  );  //
        Thread.sleep(5000);
        System.out.println( "sleep out...."  );  //
        return this.acceptStr + " append some chars and return it!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new CallableImpl("my callable test!");
        FutureTask<String> task = new FutureTask<>(callable);
        long beginTime = System.currentTimeMillis();
        // 创建线程
        new Thread(task).start();  //调用call() 不会阻塞线程
        Thread.sleep(1000);
        System.out.println( "start method ......" );
        // 调用get()阻塞主线程，反之，线程不会阻塞
        String result = task.get();
        long endTime = System.currentTimeMillis();
        System.out.println("hello : " + result);
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }
}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AsyncThread
 * Author:   martin
 * Date:     2018/11/27 13:47
 * Description: 异步线程demo
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.*;
import java.util.concurrent.*;

public class AsyncThreadPool {

    public static void main(String[] args) {

        AsyncThreadPool at = new AsyncThreadPool();
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        at.generate(3, futureList);
        at.doOtherThings();
        at.getResult(futureList);

    }

    /**
     * 生成指定数量的线程，都放入future数组
     *
     * @param threadNum
     * @param fList
     */
    public void generate(int threadNum, List<Future<String>> fList) {
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        Random random = new Random();
        for (int i = 0; i < threadNum; i++) {
            Future<String> f = service.submit(getJob(i));
            fList.add(f);
        }
        service.shutdown();
    }

    /**
     * 生成指定序号的线程对象
     *
     * @param i
     * @return
     */
    public Callable<String> getJob(final int i) {
        final int time = new Random().nextInt(10);
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("job call...."+time);
                return "thread-" + i +"_"+ time;
            }
        };
    }


    /**
     * other things
     */
    public void doOtherThings() {
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println("do sleep thing no:" + i);
                Thread.sleep(1000 * (new Random().nextInt(10)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从future中获取线程结果，打印结果
     *
     * @param fList
     */
    public void getResult(List<Future<String>> fList) {
        int listCount = fList.size();
        int tempTotal = 0 ;
        for (Future<String> future:fList  ) {
            try {
                while (true) {
                    if ( future.isDone() ){
                        System.out.println( "future get result:"+ future.get() );
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


}

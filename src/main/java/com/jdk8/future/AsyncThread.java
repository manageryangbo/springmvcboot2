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
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *     异步还需验证 ?
 */
@Component
public class AsyncThread {

    public static void main(String[] args) {

        AsyncThread at = new AsyncThread();
        List<Future<Map<String, List<String>>>> futureList = new ArrayList<Future<Map<String, List<String>>>>();
        at.generate(4, futureList);
        at.getResult(futureList);

    }

    /**
     * 生成指定数量的线程，都放入future数组
     *
     * @param threadNum
     * @param fList
     */
    public void generate(int threadNum, List<Future<Map<String, List<String>>>> fList) {
        for ( int futureCount = 0;futureCount< threadNum ;futureCount++){
            Future<Map<String, List<String>>> _future = asyncHanlderUnit();
            fList.add( _future );
        }
    }

    /**
     * 异步调用单元(非线程池)
     * @return
     */
    @Async
    public Future<Map<String, List<String>>> asyncHanlderUnit(){
        Map<String, List<String>> returnMap = new HashMap<String, List<String>>();
        List<String> details = new ArrayList<String>();
        Random random = new Random();
        int mark = random.nextInt(1000);
        for (int count=0;count<10;count++){
            details.add(   "mark_"+mark+"_"+count ) ;
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "==========" + mark+"_"+count );
        }
        returnMap.put("data", details);
        return new AsyncResult<Map<String, List<String>>>(returnMap);
    }


    /**
     * other things
     */
    public void doOtherThings() {
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println("do thing no:" + i);
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
    public void getResult(List<Future<Map<String, List<String>>>> fList) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(getCollectJob(fList));
        service.shutdown();
    }

    /**
     * 生成结果收集线程对象
     *
     * @param fList
     * @return
     */
    public Runnable getCollectJob(final List<Future<Map<String, List<String>>>> fList) {
        return new Runnable() {
            public void run() {
                for (Future<Map<String, List<String>>> future : fList) {
                    try {
                        while (true) {
                            if (future.isDone() && !future.isCancelled()) {
                                System.out.println("Future:" + future
                                        + ",Result:" + future.get().get("data"));
                                break;
                            } else {
                                Thread.sleep(1000);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

}

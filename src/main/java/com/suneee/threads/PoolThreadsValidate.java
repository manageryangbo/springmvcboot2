/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PoolThreadsValidate
 * Author:   martin
 * Date:     2018/8/14 15:10
 * Description: 线程池测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;


import java.util.Vector;
import java.util.concurrent.*;

public class PoolThreadsValidate {

    private static final ExecutorService executors = Executors.newCachedThreadPool();
    private static final Vector<String> codeQueue = new Vector<>();

    public static void main(String[] args)  throws Exception{
        PoolThreadsValidate pdv = new PoolThreadsValidate();
        UnitJob unitJob_1 = new UnitJob();
        unitJob_1.setRid(1);
        unitJob_1.setName("西红柿.....");
        unitJob_1.setAcount(3);
        UnitJob unitJob_2 = new UnitJob();
        unitJob_2.setRid(2);
        unitJob_2.setName("香蕉.....");
        unitJob_2.setAcount(3);
        pdv.addJobs( unitJob_1 );
        pdv.addJobs( unitJob_2 );

        executors.shutdown();  // 不关线程池，主线程就不会关闭

    }

    // 只要addJob跑完就可以再调用，线程有没有执行完不影响它调用
    public synchronized void addJobs(UnitJob unitJob) {
        if (unitJob == null)
            return;
        String modelInfo = unitJob.getRid() + unitJob.getName();
        if (codeQueue.contains(modelInfo)) {
            return;
        }
        MakeCouponCodeJob job = new MakeCouponCodeJob(unitJob);
        Future<String> futureJob = new FutureTask<String>(job) {
            @Override
            protected void done() {
                try {
                    codeQueue.remove(modelInfo);
                } catch (Exception e) {
                    codeQueue.remove(modelInfo);
                }
                super.done();
            }
        };
        try {
            executors.execute((Runnable) futureJob);
            codeQueue.add(modelInfo);
//            Thread.sleep(1000);
        } catch (RejectedExecutionException e) {
            codeQueue.remove(modelInfo);
        } catch( Exception e){
            System.out.println( "Exception ...." );
        }

    }

    class MakeCouponCodeJob implements Callable<String> {

        private UnitJob unitJob;
        public MakeCouponCodeJob() {
        }
        private MakeCouponCodeJob(UnitJob unitJob) {
            this.unitJob = unitJob;
        }

        @Override
        public String call() throws Exception {
            try {
                createQRCodeForActivity(unitJob);
                System.out.println("线程-"   + Thread.currentThread().getName() );
            } catch (Exception e) {
                throw e;
            }
            String result = unitJob.getRid() + unitJob.getName();
            unitJob = null;
            return result;
        }

    }

    private void createQRCodeForActivity(UnitJob unitJob){
        for(int i=0;i<unitJob.getAcount();i++){
            try{
                Thread.sleep(100);
                System.out.println(unitJob.getName()+"<===>"+unitJob.getRid());
            }catch (Exception e){
                System.out.println("存在异常....");
            }
        }
    }
}

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: VolatileDemo
 * Author:   martin
 * Date:     2019/5/20 14:52
 * Description: 验证volatile只有可见性，无原子性
 *          执行结果:每次的结果都不相同
 *          原因是声明为volatile的变量若与自身相关，如以下的声明方式：n=n+1,n++等，
 *          那么声明为volatile的变量就不起作用，也就是说关键字volatile无效。
 *
 *          volatile禁止重排序，即执行循序不会受CPU规则的影响
 *          volatile标记的变量不会被编译器优化；synchronized标记的变量可以被编译器优化，即指令重排序
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.volatiles;

public class VolatileDemo {

    /**
     * volatile 只有可见性 无法保证原子性
     */
    private volatile int num = 0 ;

    public int getNum(){
        return this.num ;
    }

    public  void increase(){  //synchronized
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.num ++ ;
    }

    public static void main(String[] args) {
        final VolatileDemo volatileDemo = new VolatileDemo();
        for (int i = 0 ; i< 500;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileDemo.increase();
                }
            }).start();
        }
        // 如果还存在子线程在执行，主线程让出cpu资源，直到子线程执行完毕
        while ( Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println( "========num:"+volatileDemo.getNum() );
    }

}

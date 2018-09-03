/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JoinTest
 * Author:   martin
 * Date:     2018/8/20 9:54
 * Description: Join测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

public class JoinTest {
    public static void main(String [] args) throws InterruptedException {
        ThreadJoinTest t1 = new ThreadJoinTest("小明");
        ThreadJoinTest t2 = new ThreadJoinTest("小东");
        t1.start();
        /**join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
         程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
         所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
         */
        t1.join(100); //如果join加入10参数，表示先执行t1线程，10毫秒后，主线程和t1一起并行执行
        System.out.println("执行完了t1线程，回到main线程");  // join设置参数值大于0时(如t1.join(10)) 不会打印
        t2.start();
    }
}
class ThreadJoinTest extends Thread{
    public ThreadJoinTest(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<100000;i++){
            System.out.println(this.getName() + ":" + i);
        }
    }
}

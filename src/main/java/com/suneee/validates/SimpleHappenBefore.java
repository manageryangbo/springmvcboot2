/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SimpleHappenBefore
 * Author:   martin
 * Date:     2018/7/23 16:57
 * Description: 指令重排序
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.validates;

/**
 * 来源 : http://www.cnblogs.com/mengheng/p/3495379.html
 * 一个简单的展示Happen-Before的例子.
 * 这里有两个共享变量:a和flag,初始值分别为0和false.在ThreadA中先给a=1,然后flag=true.
 * 如果按照有序的话,那么在ThreadB中如果if(flag)成功的话,则应该a=1,而a=a*1之后a仍然为1,下方的if(a==0)应该永远不会为真,永远不会打印.
 * 但实际情况是:在试验100次的情况下会出现0次或几次的打印结果,而试验1000次结果更明显,有十几次打印.
 *
 *   总结:    并发专家建议我们远离volatile是有道理的
 *      volatile是在synchronized性能低下的时候提出的。如今synchronized的效率已经大幅提升，所以volatile存在的意义不大。
 *      如今非volatile的共享变量，在访问不是超级频繁的情况下，已经和volatile修饰的变量有同样的效果了。
 *      volatile不能保证原子性，这点是大家没太搞清楚的，所以很容易出错。
 *      volatile可以禁止重排序。
 */
public class SimpleHappenBefore {
    /** 这是一个验证结果的变量 */
    private volatile static int a=0;
    /** 这是一个标志位 */
    private volatile static boolean flag=false;

    public static void main(String[] args) throws InterruptedException {
        //由于多线程情况下未必会试出重排序的结论,所以多试一些次
        for(int i=0;i<1000;i++){
            ThreadA threadA=new ThreadA();
            ThreadB threadB=new ThreadB();
            threadA.start();
            threadB.start();

            //这里等待线程结束后,重置共享变量,以使验证结果的工作变得简单些.
            threadA.join();
            threadB.join();
            a=0;
            flag=false;
        }
    }

    static class ThreadA extends Thread{
        public void run(){
            a=1;
            flag=true;
        }
    }

    static class ThreadB extends Thread{
        public void run(){
            if(flag){
                a=a*1;
            }
            if(a==0){
                System.out.println("ha,a==0");
            }
        }
    }
}

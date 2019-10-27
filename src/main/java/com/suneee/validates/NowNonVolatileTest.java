/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test
 * Author:   martin
 * Date:     2019/6/17 10:32
 * Description:
 *          原来只有在对变量读取频率很高的情况下，虚拟机才不会及时回写主内存，
 *          而当频率没有达到虚拟机认为的高频率时，普通变量和volatile是同样的处理逻辑。
 *          如在每个循环中执行System.out.println(2)加大了读取变量的时间间隔，
 *          使虚拟机认为读取频率并不那么高，所以实现了和volatile的效果。
 *          volatile的效果在jdk1.2及之前很容易重现，但随着虚拟机的不断优化，
 *          如今的普通变量的可见性已经不是那么严重的问题了，这也是volatile如今确实不太有使用场景的原因
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.validates;

public class NowNonVolatileTest extends Thread {
    //设置类静态变量,各线程访问这同一共享变量
    private static boolean flag = false;

    //无限循环,等待flag变为true时才跳出循环
    public void run() {
        while (!flag) {
            // 当没有这句打印语句是，此线程JVM会认为是高频率调用变量，只会在缓存中取，所有flag一直为false
            // 有这句打印后，此线程JVM就不会认为是高频率调用变量，在主内存中取
//            System.out.println(2);
        }
    }

    public static void main(String[] args) throws Exception {
        new NowNonVolatileTest().start();
        //sleep的目的是等待线程启动完毕,也就是说进入run的无限循环体了
        Thread.sleep(100);
        flag = true;
    }

}

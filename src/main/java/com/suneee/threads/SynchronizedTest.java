/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SyncTest
 * Author:   martin
 * Date:     2018/8/29 11:38
 * Description: synchronized修饰静态方法时,调用该方法的对象的类的所有对象被锁
 *                          修饰普通方法时,仅调用该方法的对象被锁
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedTest {

        private static int num;

        public static synchronized void method01(String arg) {
            try {
                if("a".equals(arg)){
                    num = 100;
                    System.out.println("method01 tag a set number over");
                    Thread.sleep(1000);
                }else{
                    num = 200;
                    System.out.println("method01 tag b set number over");
                }
                Thread.sleep(1000);
                System.out.println("method01 tag = "+ arg + ";num ="+ num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static synchronized void method02(String arg) {
            try {
                if("a".equals(arg)){
                    num = 100;
                    System.out.println("method02 tag a set number over");
                    Thread.sleep(1000);
                }else{
                    num = 200;
                    System.out.println("method02 tag b set number over");
                }
                Thread.sleep(1000);
                System.out.println("method02 tag = "+ arg + ";num ="+ num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            final SynchronizedTest m1 = new SynchronizedTest();
            final SynchronizedTest m2 = new SynchronizedTest();

            Thread t1 = new Thread(new Runnable() {

                @Override
                public void run() {
                    m1.method01("a");
                }
            });
            t1.start();

            Thread t2 = new Thread(new Runnable() {

                @Override
                public void run() {
                    m2.method02("b");
                }
            });
            t2.start();

            List<String> listA = new ArrayList<String>();
            listA.add("a");
            listA.add("b");
            listA.add("c");

            List<String> listB = new ArrayList<String>();
            listB.add("a");
            listB.add("b");
//            listB.add("c");
            listB.add("d");
            boolean isFlag = listA.retainAll( listB );
            System.out.println( "isFlag:"+ isFlag );

        }

}

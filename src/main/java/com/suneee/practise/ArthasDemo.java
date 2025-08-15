/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Test
 * Author:   martin
 * Date:     2018/6/1 14:30
 * Description: 常规练习
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.practise;

import java.io.*;
import java.util.*;

public class ArthasDemo extends Thread {

    public ArthasDemo() {
    }

    public ArthasDemo(String threadName) {
        super(threadName);
    }

    public static void main(String[] args) {

        new ArthasDemo("ArthasDemoThread1").start();

    }

    @Override
    public void run() {
        List<Student> studentList = new ArrayList<>();
        try{
            HashMap<String,String> map = new HashMap<>();
            map.put("sd","sd");
            for (int i=0;i<10000;i++){
                Thread.sleep(100);
                Student stu = new Student();
                stu.setSname("Sname"+ i);
                studentList.add(stu);
                System.out.println(Thread.currentThread().getName()+"zhixingle"+i);
            }
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

    }
}

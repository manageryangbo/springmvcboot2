/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test2
 * Author:   martin
 * Date:     2019/3/12 9:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.practise;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Student s = new Student();
        s.setAge(12);
        studentList.add( s );
//        resetStudentList(studentList);
        for (Student tstudent: studentList) {
            resetStudent(tstudent);
        }
        for (Student tsstudent: studentList) {
            System.out.println( tsstudent.getAge() );
        }

        Map<String,Map<String,Student>> studentMapArr = new HashMap<>();

        Map<String,Student> studentMap= new HashMap<>();
        studentMap.put("s2",new Student());
        studentMapArr.put("ss2",studentMap);
        Map<String, Student> ss2 = studentMapArr.get("ss2");
        Student s2 = new Student();
        s2.setAge(18);
        ss2.put("s2",s2);
        Student student2 = ss2.get("s2");
        student2.setAge(45);
//        for (String _ss2key:studentMapArr.keySet()){
//            if(_ss2key.equals("ss2")){
//                Map<String,Student> _studentMap = studentMapArr.get(_ss2key);
//                for (String _s2key:_studentMap.keySet()){
//                    if(_s2key.equals("s2")){
//                        Student _s2Studnet =  _studentMap.get(_s2key);
//                        if(_s2Studnet.getAge()==null){
//                            _s2Studnet.setAge(  16 );
//                        }else{
//                            _s2Studnet.setAge(  _s2Studnet.getAge()+17 );
//                        }
//
//                    }
//                }
//            }
//        }
        Student _s2Studnet =  studentMapArr.get("ss2").get("s2");
        if(_s2Studnet.getAge()==null){
            _s2Studnet.setAge(  13 );
        }else{
            _s2Studnet.setAge(  _s2Studnet.getAge()+19 );
        }
        System.out.println("=================");
        for (String __ss2key:studentMapArr.keySet()){
            if(__ss2key.equals("ss2")){
                Map<String,Student> __studentMap = studentMapArr.get(__ss2key);
                for (String __s2key:__studentMap.keySet()){
                    if(__s2key.equals("s2")){
                        Student __s2Studnet =  __studentMap.get(__s2key);
                        System.out.println(  __s2Studnet.getAge() );
                    }
                }
            }
        }
        System.out.println(  studentMapArr.get("ss2").get("s2").getAge() );

        List<Student> studentsList = new ArrayList<>();

    }

    public static void resetStudentList(List<Student> studentList){
        for (Student tstudent: studentList) {
            tstudent.setAge(13);
        }
    }
    public static void resetStudent(Student student){
        student.setAge(14);
    }
}

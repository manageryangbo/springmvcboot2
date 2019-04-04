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
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        // 数组和集合的(类型和大小)最好同步 (String&&list.size)
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");
        String[] array = new String[list.size()];
        array = list.toArray(array);

        String[] str = new String[] { "a", "b" };
        List asList = Arrays.asList(str);
//        asList.add("c");
//        Arrays.asList 体现的是适配器模式，只是转换接口，asList后台的数据仍是数组
//        运行时抛:UnsupportedOperationException
        str[0] = "gujin";
        System.out.println( asList.get(0) );  // 数组改变值时，转换过来的集合值也会变化

        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {  //反例
            if ("1".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(  a.toString() );
        Iterator<String> it = a.iterator();
        while (it.hasNext()) {  //正例
            String temp = it.next();
            if ("2".equals(temp)) {
                it.remove();
            }
        }
        System.out.println(  a.toString() );


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("first","第一天");
        map.put("second","第二天");
        map.forEach((k,v)->System.out.println("jdk8 【key:" + k + ";value:" + v + "】"));  // jdk8
        Set<Map.Entry<String, Object>> entriesMap = map.entrySet();
        for (Map.Entry<String,Object> entry : entriesMap){
            System.out.println("entry【key:"+entry.getKey()+";value:"+entry.getValue()+"】");
        }


//        System.out.println( String.format("mall_goods:%s:%s:%s","1234545","NJXS","1613") );

        List<Student> newList2 = new ArrayList<>();
        for (Student sss:  newList2 ) {  //  不能为空
            System.out.println("==");
        }

        cloneTest();
    }

    public static void cloneTest() throws CloneNotSupportedException {
        List<Student> studentList = new ArrayList<>();
        Student s =new Student();
        s.setSid(1);
        s.setAllscore(80);
        s.setAge(25);
        s.setDeleted( 0 );
        Student s2 =new Student();
        s2.setSid(2);
        s2.setAllscore(95);
        s2.setAge(26);
        s2.setDeleted( 0 );
        studentList.add(s);studentList.add(s2);
        int star = 25;
        Classes cls = new Classes();
        cls.setName("1001");
        cls.setAllscore(5500);
        cls.setStudentList( studentList );
        List<Student> studentListA = deepCopyList(studentList) ;
        List<Student> studentListB = deepCopyList(studentList) ;
//        for (Student students: studentList) {
//            studentListA.add(    students);
//            studentListB.add(   students );
////            studentListA.add(   (Student) students.clone() );
////            studentListB.add(  (Student) students.clone() );
//        }
        for (Student  student:   studentListA) {
            student.setDeleted(1);
        }
        studentListA.add( s2 );
        for (Student  students:   studentListB) {
            System.out.println( students.getDeleted() );
        }

        List<Student> newList = studentList.stream()
                .filter(item -> item.getAllscore() > 70)
                .collect(Collectors.toList());
        newList.sort(Comparator.comparing(Student::getAge).reversed());
        System.out.println(newList.toString());
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> deepCopyList(List<T> src)
    {
        List<T> dest = null;
        try{
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();
        }catch (IOException e){
            e.getStackTrace();
        }catch (ClassNotFoundException e){
            e.getStackTrace();
        }
        return dest;
    }
}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Classes
 * Author:   martin
 * Date:     2018/12/16 13:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.practise;

import java.util.ArrayList;
import java.util.List;

public class Classes{
    private String name;
    private Integer allscore ;
    List<Student> studentList ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAllscore() {
        return allscore;
    }

    public void setAllscore(Integer allscore) {
        this.allscore = allscore;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }


}

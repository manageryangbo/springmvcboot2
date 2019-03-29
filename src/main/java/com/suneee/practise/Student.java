/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Student
 * Author:   martin
 * Date:     2018/12/13 15:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.practise;

import java.io.Serializable;
import java.util.Date;

public class Student  implements Serializable ,   Cloneable{

    private Integer sid ;
    private Integer age;
    private Integer allscore;
    private Integer deleted;
    private Date date ;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAllscore() {
        return allscore;
    }

    public void setAllscore(Integer allscore) {
        this.allscore = allscore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", age=" + age +
                ", allscore=" + allscore +
                '}';
    }
}

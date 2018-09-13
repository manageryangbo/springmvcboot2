/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UnitJob
 * Author:   martin
 * Date:     2018/8/14 15:18
 * Description: 单位任务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.threads;

public class UnitJob {
    private int rid;
    private String name ;
    private int acount ;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getAcount() {
        return acount;
    }

    public void setAcount(int acount) {
        this.acount = acount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

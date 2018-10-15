/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Activity
 * Author:   martin
 * Date:     2018/9/20 11:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.stream;

import java.util.List;

public class Activity {
    private Integer id;
    List<Goods> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }
}

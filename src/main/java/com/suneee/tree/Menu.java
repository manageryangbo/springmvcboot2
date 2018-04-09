/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Menu
 * Author:   martin
 * Date:     2018/4/8 16:28
 * Description: 树架构
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.tree;

public class Menu {
    private Integer id ;
    private Integer parentId ;
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", level=" + level +
                '}';
    }
}

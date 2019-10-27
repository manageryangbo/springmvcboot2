/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Promotion
 * Author:   martin
 * Date:     2019/7/11 9:54
 * Description: 促销表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.strategy;

public class Promotion {

    private Integer pId ;

    private String pType;

    private String pName ;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }


}

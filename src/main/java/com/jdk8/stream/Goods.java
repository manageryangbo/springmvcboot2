/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Goods
 * Author:   martin
 * Date:     2018/9/20 11:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.stream;

public class Goods {
    private Integer price;
    private String name;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Goods(Integer price, String name) {
        this.price = price;
        this.name = name;
    }
}

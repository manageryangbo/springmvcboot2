/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Customer
 * Author:   martin
 * Date:     2018/7/13 11:27
 * Description: 客户枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.model;

public enum Customer {

    ZERO(0,"ZERO"),
    ONE(1,"ONE"),
    TWO(2,"TWO");


    private int index;
    private String name;

    private Customer( int index,String name) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public static Customer getEnumByIndex(int index) {
        for (Customer c : Customer.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }

}

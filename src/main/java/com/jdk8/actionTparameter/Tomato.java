/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Tomato
 * Author:   martin
 * Date:     2018/4/26 14:58
 * Description: 西红柿基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.actionTparameter;

public class Tomato {
    private String color;
    private Integer weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Tomato{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }


}

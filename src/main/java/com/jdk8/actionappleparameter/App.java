/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: App
 * Author:   martin
 * Date:     2018/4/25 14:24
 * Description: 苹果基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.actionappleparameter;

public class App {
    private String color;
    private Integer weight;

    public App() {
    }

    public App(Integer weight) {
        this.weight = weight;
    }

    public App(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

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
        return "App{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Dish
 * Author:   martin
 * Date:     2018/5/2 14:57
 * Description: 基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.stream;


public class Dish {
    private Integer calories;
    private String name ;
    private Type type;
    private boolean vegetarian ;

    public Dish() {
    }

    public Dish(Integer calories, String name, Type type, boolean vegetarian) {
        this.calories = calories;
        this.name = name;
        this.type = type;
        this.vegetarian = vegetarian;
    }

    public enum Type {  MEAT ,FISH , OTHER }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "calories=" + calories +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", vegetarian=" + vegetarian +
                '}';
    }

}

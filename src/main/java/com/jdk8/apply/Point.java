/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Point
 * Author:   martin
 * Date:     2018/6/11 16:55
 * Description: 坐标类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.apply;

public class Point {
    private final int x;
    private final int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveRightBy(int x){
        return new Point(this.x+x,this.y);
    }


}

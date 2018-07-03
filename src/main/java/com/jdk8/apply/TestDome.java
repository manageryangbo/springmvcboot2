/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestDome
 * Author:   martin
 * Date:     2018/6/11 16:58
 * Description: 测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.apply;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestDome {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        BigDecimal bd2 = new BigDecimal("1.1");
        double d = 1.1;
        BigDecimal bd1 = BigDecimal.valueOf(d);
        int i = 42;
        double d1 = Double.longBitsToDouble(i);
    }
}

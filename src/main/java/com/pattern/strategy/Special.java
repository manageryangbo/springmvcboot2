/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Discount
 * Author:   martin
 * Date:     2019/7/11 10:13
 * Description: 折扣计算
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.strategy;

import java.math.BigDecimal;

public class Special implements IStrategy{

    public BigDecimal calPriceRule(BigDecimal price){
        System.out.println(price + "cal Special Rule ");
        return null;
    }

}

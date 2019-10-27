/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ContextStrategy
 * Author:   martin
 * Date:     2019/7/11 10:21
 * Description: 策略环境
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.strategy;


import java.math.BigDecimal;

public class ContextStrategy {

    private IStrategy iStrategy;

    public ContextStrategy(Integer pType){

        PROM_TYPE enumResult = PROM_TYPE.getEnumClassByKey(pType);
        switch(enumResult){
            case PROM_SPECIAL:
                iStrategy = new Special();
                break;
            case PROM_DISCOUNT:
                iStrategy = new Discount();
                break;
            case PROM_REDUCE:
                iStrategy = new Reduce();
                break;
            default:
                iStrategy = new Reduce();
                break;
        }
    }

    public BigDecimal calGoodsPrice(BigDecimal price){
        return iStrategy.calPriceRule(price);
    }

}

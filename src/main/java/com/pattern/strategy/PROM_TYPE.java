/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: PROM_TYPE
 * Author:   martin
 * Date:     2019/7/11 10:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.pattern.strategy;

public enum PROM_TYPE {

    PROM_SPECIAL(1,"促销特价"), PROM_DISCOUNT(2,"促销折扣"), PROM_REDUCE(3,"促销满减");

    private Integer key;
    private String value;

    PROM_TYPE(Integer key,String value)
    {
        this.key = key;
        this.value = value;
    }

    public static PROM_TYPE getEnumClassByKey(Integer key){

        for ( PROM_TYPE pType :  PROM_TYPE.values()){
            if(pType.key.equals( key ) ){
                return pType ;
            }
        }
        throw new IllegalArgumentException("无效的枚举值");

    }

    public Integer getKey() {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}

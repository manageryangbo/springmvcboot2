/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: GoodsTypeEnum
 * Author:   martin
 * Date:     2018/11/7 15:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.lambda;

public enum GoodsTypeEnum {
    GENERAL_TYPE("普通商品", Integer.valueOf(2)),
    RAW_MATERIAL_TYPE("原料商品", Integer.valueOf(4)),
    CONSUMABLES_TYPE("耗材商品", Integer.valueOf(5)),
    WEIGHT_BY_ONE_TYPE("称重按个", Integer.valueOf(6)),
    WEIGHT_BY_SALE_TYPE("称重散卖", Integer.valueOf(7)),
    SHOPPING_BAG_TYPE("购物袋", Integer.valueOf(8)),
    DISH_TYPE("菜品", Integer.valueOf(9));

    private String typeName;
    private Integer type;

    private GoodsTypeEnum(String typeName, Integer type) {
        this.typeName = typeName;
        this.type = type;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

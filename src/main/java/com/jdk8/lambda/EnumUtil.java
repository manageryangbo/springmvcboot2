/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EnumUtil
 * Author:   martin
 * Date:     2018/11/7 15:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.jdk8.lambda;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class EnumUtil {
    private static Map<Class<?>, Object> enumMap = new ConcurrentHashMap<>();

    /**
     * 改进后的方法，不需要初始化一大堆的map. 设置自己的规则
     */
    public static <T> T getEnumObject(Class<T> clazz, Predicate<T> predicate) {
        if (!clazz.isEnum()) {
            throw new IllegalArgumentException("非枚举类型");
        }

        Object mapValue = enumMap.get(clazz);
        if (mapValue == null) {
            enumMap.put(clazz, clazz.getEnumConstants());
            mapValue = enumMap.get(clazz);
        }

        @SuppressWarnings("unchecked")
        T[] enumArray = (T[]) mapValue;
        Optional<T> optional = Arrays.stream(enumArray).filter(predicate).findAny();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("无效的枚举值:");
    }

    public static void main(String[] args) {
        int freeGoods = 8;
        EnumUtil.getEnumObject(GoodsTypeEnum.class,tmp -> tmp.getType().equals(freeGoods)).getTypeName();
    }

}

package com.utils.selectors;

import java.util.Date;

/**
 * 简单数据类型选择器(数字，字符，时间类型选择)
 * @author MHH
 *
 */
public class SimpleTypeSelector implements FieldSelectorI {

	public boolean include(Object field, String fieldName) {
        return field instanceof Number || field instanceof String || field instanceof Date;
    }

}

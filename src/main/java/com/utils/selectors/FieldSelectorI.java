package com.utils.selectors;

/**
 * 复制类属性的时候的属性选择器接口
 * @author MHH
 * 2007-12-08
 */
public interface FieldSelectorI {

	public boolean include(Object field, String fieldName);
}

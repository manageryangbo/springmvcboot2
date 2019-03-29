package com.utils.selectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 空字符串选择器
 * @author MHH
 * 2007-12-08
 */
public class BlankStringSelector implements FieldSelectorI {
	
	private boolean isInclude;
	
	public BlankStringSelector(boolean isInclude) {
		this.isInclude = isInclude;
	}

	public boolean include(Object field, String fieldName) {
		boolean isSelect = true;
		if (field instanceof String) {
			if (StringUtils.isBlank(field.toString())) {
				isSelect = isInclude;
			}
		}
		return isSelect;
	}

}

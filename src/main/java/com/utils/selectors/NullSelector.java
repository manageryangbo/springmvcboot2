package com.utils.selectors;

/**
 * 排除空属性的选择器
 * @author MHH
 * 2007-12-08
 */
public class NullSelector implements FieldSelectorI {
	
	private boolean isInclude;
	
	public NullSelector(boolean isInclude) {
		this.isInclude = isInclude;
	}

	public boolean include(Object field, String fieldName) {
		boolean isSelect = true;
		if (field == null) {
			isSelect = isInclude;
		}
		return isSelect;
	}


}

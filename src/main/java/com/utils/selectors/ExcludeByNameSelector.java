package com.utils.selectors;

import org.apache.commons.lang3.ArrayUtils;

public class ExcludeByNameSelector implements FieldSelectorI {
	private String[] excludeField;

	public ExcludeByNameSelector(String[] excludeField) {
		this.excludeField=excludeField;
	}

	public boolean include(Object field, String fieldName) {
		if (excludeField!=null&& ArrayUtils.contains(excludeField, fieldName)) {
			return false;
		}
		return true;
	}

}

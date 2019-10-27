package com.utils;

import com.utils.selectors.BlankStringSelector;
import com.utils.selectors.FieldSelectorI;
import com.utils.selectors.NullSelector;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 类复制工具
 * <p>
 * hwz 修改 20090609
 * <p>
 * 原来的copy方法，不支持继承的属性，现在修改为支持继承
 * 
 * @author MHH 2007-12-08
 */
public class ClassCopyUtils {

	private static final Logger log = LoggerFactory.getLogger(ClassCopyUtils.class);

	private List<FieldSelectorI> selectorList = new ArrayList<FieldSelectorI>(1);

	public ClassCopyUtils() {
		// 默认选择非空的属性
		addSelector(new NullSelector(false));
	}

	public void copy(Object source, Object dest) {
		Validate.isTrue(source != null && dest != null, "空值不能进行属性复制!");
		String fieldOrignStr = null;
		String fieldName = null;

		Method[] sourcrMethods = source.getClass().getMethods();
		for (Method sourceMethod : sourcrMethods) {
			// 必须是程序能正常访问到的属性 public Object getXXX()
			if (sourceMethod.getName().startsWith("get")
					&& !sourceMethod.getName().equals("getClass")
					&& Modifier.isPublic(sourceMethod.getModifiers())
					&& sourceMethod.getParameterTypes().length == 0) {
				try {
					Object value = sourceMethod.invoke(source);
					boolean include = true;
					for (FieldSelectorI selector : selectorList) {
						if (!selector.include(value, null)) {
							include = false;
							break;
						}
					}
					if (include) {
						fieldOrignStr = sourceMethod.getName().substring(3);
						fieldName = fieldOrignStr.substring(0, 1).toLowerCase()
								+ fieldOrignStr.substring(1);
						BeanUtils.copyProperty(dest, fieldName, value);
						log.debug(fieldName + "赋值成功");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					log.error("目标不具有访问性" + e.getMessage());
				} catch (InvocationTargetException e) {
					log.error("方法调用出现异常:" + e.getMessage());
				}
			}
		}

	}

	public void includeBlankString(boolean include) {
		addSelector(new BlankStringSelector(include));
	}

	public void addSelector(FieldSelectorI selector) {
		this.selectorList.add(selector);
	}

}

/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：ValidateUtil.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class Validate util.
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtil {
	/**
	 * 校验手机号码是否合法.
	 *
	 * @param mobile the mobile
	 *
	 * @return the boolean
	 */
	public static boolean isMobileNumber(final String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			return false;
		}
		final String reg = "^((\\+?86)|(\\(\\+86\\)))?(13[0-9][0-9]{8}|14[0-9]{9}|15[0-9][0-9]{8}|17[0-9][0-9]{8}|18[0-9][0-9]{8})$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}
	/**
	 * 检测邮箱地址是否合法
	 * @param email
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String email){
		if (StringUtils.isEmpty(email)) return false;
//        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}


}

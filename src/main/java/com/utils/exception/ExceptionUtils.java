package com.utils.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionUtils {
	private static final Logger log = LoggerFactory.getLogger(ExceptionUtils.class);

	public static String getMessage(Exception ex) {
		return getRootMessage(ex);
	}

	/**
	 * 获取根部的throwable，如果深度>1000，则取第1000个；
	 * 
	 * @param t
	 * @return
	 */
	public static Throwable getRootThrowable(Throwable t) {
		int i = 0;
		while (t != null && t.getCause() != null) {
			t = t.getCause();
			i++;
			if (i > 1000) {
				if (log.isDebugEnabled()) {
					log.debug("获取Throwable深度超过1000,取第1000个！");
				}
				break;
			}
		}
		return t;
	}

	/**
	 * 获取根部的message，如果深度>1000，则取第1000个；
	 * 
	 * @param t
	 * @return
	 */
	public static String getRootMessage(Throwable t) {
		String message = "未知错误!";
		t = getRootThrowable(t);
		if (t instanceof java.sql.SQLException) {
			message = "数据库操作异常:" + t.getMessage();
		} else {
			message = t.getMessage();
		}
		return message;
	}
}

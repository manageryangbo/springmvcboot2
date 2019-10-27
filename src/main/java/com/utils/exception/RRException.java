package com.utils.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常
 * 
 * @author chenjinjie
 * @email guangyaoliangzijie@163.com
 * @date 2016年10月27日 下午10:11:27
 */
@Slf4j
public class RRException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public RRException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public RRException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
		//错误信息
		log.error("系统遇到如下异常，异常码：{}>>>异常信息：{}", code, msg);
	}
	/**
	 * 构造函数设置错误码以及错误参数列表
	 *
	 * @param code 错误码
	 * @param msg  获取格式化后的异常消息内容
	 */
	public RRException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
		//错误信息
		log.error("系统遇到如下异常，异常码：{}>>>异常信息：{}", code, msg);
	}
	
	public RRException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
		//错误信息
		log.error("系统遇到如下异常，异常码：{}>>>异常信息：{}", code, msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


}

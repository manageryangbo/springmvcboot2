package com.utils.exception;

import com.mongodb.DuplicateKeyException;
import com.utils.content.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * 
 * @author chenjinjie
 * @email guangyaoliangzijie@163.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
@Slf4j
public class RRExceptionHandler {
	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		log.error("遇到业务逻辑异常：【{}】", e.getCode());
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		log.error(e.getMessage(), e);
		return R.error();
	}
	@ExceptionHandler(ApiRRException.class)
	public R handleApiRRException(ApiRRException e) {
		R r = new R();
		r.put("code", e.getErrno());
		r.put("msg", e.getErrmsg());
		return r;
	}

}

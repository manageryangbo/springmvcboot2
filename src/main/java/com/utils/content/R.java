package com.utils.content;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author chenjinjie
 * @email guangyaoliangzijie@163.com
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 0);
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(Object msg) {
		R r = new R();
		r.put("msg","操作成功");
		r.put("data", msg);
		return r;
	}

	public static R ok(int code, String msg,Object data) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data", data);
		return r;
	}

	public static R ok(Page page) {
		R r = new R();
		r.put("data", page.getRecords());
		//分页大小
		r.put("pageSize",page.getSize());
		//分页页码
		r.put("pageIndex",page.getCurrent());
		//页码数量
		r.put("pageCount",page.getPages());
		//数据数量
		r.put("totalCount",page.getTotal());

		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}

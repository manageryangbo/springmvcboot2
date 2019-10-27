package com.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 对外接口SIGN验证
 * 
 * @author 岑巅
 * @date 2011-1-27
 */
public class ApiUtil {

	/*
	 * 二行制转字符串
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	/**
	 * 把所有参数值转码
	 *
	 * @param params
	 * @return
	 */
	public static TreeMap<String, String> convertEncodeToMap(Map<String, String> params) {
		if (params == null)
			return null;
		Iterator it = params.entrySet().iterator();
		TreeMap<String, String> map = new TreeMap<String, String>();
		while (it.hasNext()) {
			Entry obj = (Entry) it.next();
			String name = obj.getKey().toString();
			Object valueObj = obj.getValue();
			String value = valueObj.toString();
			map.put(name, value);
		}
		return map;
	}

	public static Map<String, String> convertStringtoMap(String str) {
		if (str == null)
			return null;

		String keyvalues = str;
		if (keyvalues == null || keyvalues.length() == 0)
			return null;

		String[] keyvalueArray = keyvalues.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String keyvalue : keyvalueArray) {
			String[] s = keyvalue.split("=");
			if (s == null || s.length != 2)
				return null;
			map.put(s[0], s[1]);
			System.out.println("[" + s[0] + "] => " + s[1]);
		}
		return map;
	}

	/**
	 * 组装参数及值串
	 *
	 * @param params
	 * @return
	 */
	public static String getParamString(Map<String, String> params) {

		if (params == null)
			return null;
		Iterator it = params.entrySet().iterator();
		StringBuffer result = new StringBuffer();
		while (it.hasNext()) {
			Entry obj = (Entry) it.next();
			String name = obj.getKey().toString();
			Object[] valueObj = (Object[]) obj.getValue();
			String value = valueObj[0].toString();
			result.append(name).append("=").append(value).append("&");
		}
		return result.toString();
	}

	/*
	 * 获取签名
	 *
	 * @param params 传给服务器的参数
	 *
	 * @param token 签名命牌
	 *
	 * @return
	 */
	public static String getSign(TreeMap<String, String> params, String token) {
		String result = null;
		if (params == null)
			return result;
		Iterator<String> iter = params.keySet().iterator();
		StringBuffer orgin = new StringBuffer("");
		System.out.println("sign params list:==========");
		while (iter.hasNext()) {
			String name = iter.next();
			orgin.append(name).append(params.get(name));
			System.out.println("[" + name + "] => " + params.get(name));
		}
		System.out.println("sign params end==========");
		String paramStr = orgin.toString();
		System.out.println("paramString:" + paramStr);
		try {
			// 两次MD5转码
			MessageDigest md = MessageDigest.getInstance("MD5");
			String firstMd = ApiUtil.byte2hex(md.digest(paramStr.getBytes("utf-8"))).toUpperCase();
			firstMd += token;
			result = ApiUtil.byte2hex(md.digest(firstMd.getBytes("utf-8"))).toUpperCase();

		} catch (Exception ex) {
			throw new RuntimeException("get sign error");
		}
		System.out.println("sign:" + result);
		return result;
	}

	/**
	 * 取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	/**
	 * 京东获取sign签名
	 * @param params
	 * @param body
	 * @param secretKey
	 * @return
	 */
//	public static String getSign(String url, String body, String secretKey){
//		Map<String, String> params = getParamsFromUrl(url);
	public static String getSign(Map<String, String> params, String body, String secretKey){
		//第一步，确保参数已经排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);
		
		//第二步，把所有参数名和参数值拼接在一起(包含body体)
		String joinedParams = joinRequestParams(params, body, secretKey, keys);
		
		//第三步，使用加密算法进行加密(目前仅支持md5算法)
		String signMethod = params.get("sign_method")== null ? params.get("signType"):params.get("sign_method");
		if(!"md5".equalsIgnoreCase(signMethod)){
			return null;
		}
		byte[] abstractMessage = digest(joinedParams);
		
		//第四步， 把二进制转换成大写的十六进制
		String md5 = byte2Hex(abstractMessage);
		return md5;
	}
	
	/**
	 * 二进制转换成大写的十六进制
	 * @param bytes
	 * @return
	 */
	public static String byte2Hex(byte[] bytes){
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		int j = bytes.length;
		char str[] = new char[j * 2];
		int k = 0;
		for(byte byte0 : bytes){
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
	
	public static byte[] digest(String message){
		try{
			MessageDigest md5Instance = MessageDigest.getInstance("MD5");
			md5Instance.update(message.getBytes("UTF-8"));
			return md5Instance.digest();
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 京东SPI URL解析
	 * @param url
	 * @return
	 */
	public static Map<String, String> getParamsFromUrl(String url){
		Map<String, String> requestParams = new HashMap<String, String>();
		try{
			String fullUrl = URLDecoder.decode(url, "UTF-8");
			String[] urls = fullUrl.split("\\?");
			if(urls.length == 2){
				String[] paramArray = urls[1].split("&");
				for(String param : paramArray){
					String[] params = param.split("=");
					if(params.length == 2){
						requestParams.put(params[0], params[1]);
					}
				}
			}
		}catch(UnsupportedEncodingException e){
			return null;
		}
		return requestParams;
	}
	
	/**
	 * 京东参数拼接
	 * @param params
	 * @param body
	 * @param secretKey
	 * @param sortedKes
	 * @return
	 */
	public static String joinRequestParams(Map<String, String> params, String body, String secretKey, String[] sortedKes){
		//前面加上secretKey
		StringBuilder sb = new StringBuilder(secretKey);
		for(String key: sortedKes){
			if("sign".equals(key)){
				continue;//签名时不计算sign本身
			}else{
				String value = params.get(key);
				if(isNotEmpty(key) && isNotEmpty(value)){
					sb.append(key).append(value);
				}
			}
		}
		sb.append(body);//拼接body本身
		sb.append(secretKey);//最后加上secretKey
		return sb.toString();
	}
	
	private static boolean isNotEmpty(String s){
		return null != s && !"".equals(s);
	}

}

package com.utils;

import java.io.UnsupportedEncodingException;

public class TextUtil {

	/**
	 * 获取字符串长度
	 * 
	 * @param s
	 * @return
	 */
	public static int getWordCount(String s) {
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;

		}
		return length;

	}

	/**
	 * 判断是否是一个中文汉字
	 * 
	 * @param c
	 *            字符
	 * @return true表示是中文汉字，false表示是英文字母
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static boolean isChineseChar(char c) throws UnsupportedEncodingException {
		// 如果字节数大于1，是汉字
		// 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
		return String.valueOf(c).getBytes("UTF-8").length > 1;
	}

	/**
	 * 按字节截取字符串
	 * 
	 * @param orignal
	 *            原始字符串
	 * @param count
	 *            截取位数
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static String substring(String orignal, int count) throws UnsupportedEncodingException {
		// 原始字符不为null，也不是空字符串
		if (orignal != null && !"".equals(orignal)) {
			// 将原始字符串转换为GBK编码格式
			orignal = new String(orignal.getBytes(), "UTF-8");
			// 要截取的字节数大于0，且小于原始字符串的字节数
			if (count > 0 && count < orignal.getBytes("UTF-8").length) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					c = orignal.charAt(i);
					buff.append(c);
					if (TextUtil.isChineseChar(c)) {
						// 遇到中文汉字，截取字节总数减1
						--count;
					}
				}
				return buff.toString();
			}
		}
		return orignal;
	}
	
	/**
	 * 
	 * 取小数后两位，不四舍五入
	 * 
	 * @param str
	 * @return
	 */
	 public static String roundNum(String str){
		 try {
			 str=str.substring(0,str.indexOf("."))+str.substring(str.indexOf("."),str.indexOf(".")+3);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		  return str;
	  }
	
	

	public static void main(String[] args) {
		// 原始字符串
		String s = "我ZWR爱JAVA";
		System.out.println("原始字符串：" + s);
		try {
			System.out.println("截取前1位：" + TextUtil.substring(s, 1));
			System.out.println("截取前2位：" + TextUtil.substring(s, 2));
			System.out.println("截取前4位：" + TextUtil.substring(s, 4));
			System.out.println("截取前6位：" + TextUtil.substring(s, 6));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

package com.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class SkinUtil {
	public SkinUtil() {
	}

	public static String getStringValue(HttpServletRequest request, String name) {
		Object obj = request.getSession().getAttribute(name);
		if (obj != null)
			return obj.toString();
		else
			return getCookieValue(request, name);
	}

	public static void setStringValue(HttpServletRequest request, HttpServletResponse response,
                                      String name, String value, int saveTime) {
		saveSession(request, name, value);
		saveCookie(response, name, value, saveTime);
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		String value = null;

		if (cookie != null) {
			value = cookie.getValue();
			if (value != null) {
				value = decodeBase64(value);
				try {
					value = new String(value.getBytes("ISO-8859-1"), "GBK");
				} catch (UnsupportedEncodingException ex) {
				}
			}
		}

		return value;
	}

	/**
	 * Returns the specified cookie, or <code>null</code> if the cookie does not
	 * exist.
	 *
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            the name of the cookie.
	 * @return the Cookie object if it exists, otherwise <code>null</code>.
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		// Return null if there are no cookies or the name is invalid.
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		// Otherwise, we do a linear scan for the cookie.
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i];
			}
		}
		return null;
	}

	public static void saveSession(HttpServletRequest request, String name, String value) {
		request.getSession().setAttribute(name, value);
	}

	/**
	 * Stores a value in a cookie. This cookie will persist for the amount
	 * specified in the <tt>saveTime</tt> parameter.
	 *
	 * @param response
	 *            The HttpServletResponse object, known as "response" in a JSP
	 *            page.
	 * @param name
	 *            a name to identify the cookie
	 * @param value
	 *            the value to store in the cookie
	 * @param saveTime
	 *            the time (in seconds) this cookie should live
	 */
	public static void saveCookie(HttpServletResponse response, String name, String value,
                                  int saveTime) {
		// Check to make sure the new value is not null (appservers like Tomcat
		// 4 blow up if the value is null).
		if (value == null) {
			value = "";
		} else {
			value = encodeBase64(value);
		}
		Cookie cookie = new Cookie(name, value);
		//cookie.setMaxAge(saveTime);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * Invalidates the specified cookie.
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName) {
		// invalidate the cookie
		Cookie cookie = new Cookie(cookieName, "");
		// delete the cookie when the user closes their webbrowser
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * Encodes a String as a base64 String.
	 *
	 * @param data
	 *            a String to encode.
	 * @return a base64 encoded String.
	 */
	public static String encodeBase64(String data) {
		return encodeBase64(data.getBytes());
	}

	/**
	 * Encodes a byte array into a base64 String.
	 *
	 * @param data
	 *            a byte array to encode.
	 * @return a base64 encode String.
	 */
	public static String encodeBase64(byte[] data) {
		int c;
		int len = data.length;
		StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
		for (int i = 0; i < len; ++i) {
			c = (data[i] >> 2) & 0x3f;
			ret.append(cvt.charAt(c));
			c = (data[i] << 4) & 0x3f;
			if (++i < len)
				c |= (data[i] >> 4) & 0x0f;

			ret.append(cvt.charAt(c));
			if (i < len) {
				c = (data[i] << 2) & 0x3f;
				if (++i < len)
					c |= (data[i] >> 6) & 0x03;

				ret.append(cvt.charAt(c));
			} else {
				++i;
				ret.append((char) fillchar);
			}

			if (i < len) {
				c = data[i] & 0x3f;
				ret.append(cvt.charAt(c));
			} else {
				ret.append((char) fillchar);
			}
		}
		return ret.toString();
	}

	/**
	 * Decodes a base64 String.
	 *
	 * @param data
	 *            a base64 encoded String to decode.
	 * @return the decoded String.
	 */
	public static String decodeBase64(String data) {
		return decodeBase64(data.getBytes());
	}

	/**
	 * Decodes a base64 aray of bytes.
	 *
	 * @param data
	 *            a base64 encode byte array to decode.
	 * @return the decoded String.
	 */
	public static String decodeBase64(byte[] data) {
		int c, c1;
		int len = data.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; ++i) {
			c = cvt.indexOf(data[i]);
			++i;
			c1 = cvt.indexOf(data[i]);
			c = ((c << 2) | ((c1 >> 4) & 0x3));
			ret.append((char) c);
			if (++i < len) {
				c = data[i];
				if (fillchar == c)
					break;

				c = cvt.indexOf((char) c);
				c1 = ((c1 << 4) & 0xf0) | ((c >> 2) & 0xf);
				ret.append((char) c1);
			}

			if (++i < len) {
				c1 = data[i];
				if (fillchar == c1)
					break;

				c1 = cvt.indexOf((char) c1);
				c = ((c << 6) & 0xc0) | c1;
				ret.append((char) c);
			}
		}
		return ret.toString();
	}

	public static String makeRandom() {
		// author : herrapfel
		String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		int length = 16;
		for (int i = 0; i < length; i++) {
			int randNum = rand.nextInt(36);
			generateRandStr.append(radStr.substring(randNum, randNum + 1));
		}
		return generateRandStr + "";
	}

	private static final int fillchar = '=';
	private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz"
			+ "0123456789+/";

}

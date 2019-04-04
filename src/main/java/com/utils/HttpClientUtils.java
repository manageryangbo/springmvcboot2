package com.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
public class HttpClientUtils {

//	private static final String WXDEV_SERVER_URL = "http://wxtest.weilian.cn/b2b.php?m=b2b&c=data&a=";
	
	//	private static final String WXDEV_SERVER_URL = "http://wxdev.weilian.cn/b2b.php?m=b2b&c=data&a=";

	/**
	 * 发送 post（json）
	 *          请求访问
	 * @param method 
	 *        方法名称 例如： productLineSyc
	 * @param jsonData
	 *         json数据
	 * @param url
	 *         	商城URL
	 * @return 请求返回数据
	 * @throws IOException
	 */
	public static String postJson(String method, String jsonData,String url) throws IOException {
		String result = null;
		HttpEntity respEntity = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			HttpEntity reqEntity = null;
			if (jsonData != null) {
				reqEntity = new ByteArrayEntity(jsonData.getBytes("UTF-8"), ContentType.APPLICATION_JSON);
			}
			httpclient = HttpClients.createDefault();

			if(!"".equals(url)){
				HttpPost httppost = new HttpPost(url + method);
				log.info("httppost请求的完整url："+httppost);
				RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(30000)
						.setConnectTimeout(30000).setConnectionRequestTimeout(30000).setStaleConnectionCheckEnabled(true)
						.build();
				httppost.setConfig(defaultRequestConfig);
				if (reqEntity != null) {
					httppost.setEntity(reqEntity);
				}

				response = httpclient.execute(httppost);
				if (200 != response.getStatusLine().getStatusCode()) {// 失败
					log.error("Receive http response:" + response.getStatusLine().getStatusCode());
					return "{\"is_success\":false,\"error_code\":\"" + response.getStatusLine().getStatusCode()
							+ "\",\"message\":\"调用第三方请求失败\"}";
				}
				respEntity = response.getEntity();
				if (respEntity != null) {
					result = EntityUtils.toString(respEntity, "UTF-8");
					log.info("Receive http response:"+result);
				}
			}
		} finally {
			EntityUtils.consume(respEntity);
			close(response, httpclient);
		}
		return result;
	}

	
	public static String post(String url, Map<String, Object> parameters) {
		String result = null;
		HttpEntity respEntity = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httppost = createHttpPost(url);
			if (parameters != null && !parameters.isEmpty()) {
				List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
				Iterator<Entry<String, Object>> iter = parameters.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, Object> entry = iter.next();
					if (null != entry.getValue()) {
						if(entry.getValue()  instanceof List){
							nvps.add(new BasicNameValuePair(entry.getKey(),new ObjectMapper().writeValueAsString(entry.getValue())));
						}else{
							nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
						}
						
					}
				}
				httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			
			}
			//
			response = httpclient.execute(httppost);
			if (200 != response.getStatusLine().getStatusCode()) {// 失败
				log.error("Receive http response:" + response.getStatusLine().getStatusCode());
				return null;
			}
			respEntity = response.getEntity();
			if (respEntity != null) {
				result = EntityUtils.toString(respEntity, "UTF-8");
			}
		} catch (Exception e) {
			result="{\"message\":\"调用第三方请求失败\",\"is_success\":\"0\"}";
			log.error(e.getMessage());
		} finally {
			try {
				EntityUtils.consume(respEntity);
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
			close(response, httpclient);
		}
		return result;
	}
	
	/**
	 * 创建请求
	 * 
	 * @param url
	 * @return
	 */
	private static HttpPost createHttpPost(String url) {
		HttpPost httppost = new HttpPost(url);
	/*	BaseConfig baseConfig = SpringContextHelper.getBean(BaseConfig.class);
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(baseConfig.getSocketTimeout())
				.setConnectTimeout(baseConfig.getConnectTimeout()).setConnectionRequestTimeout(baseConfig.getConnectTimeout())
				.setStaleConnectionCheckEnabled(true).build();*/
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(300000)
				.setConnectTimeout(300000).setConnectionRequestTimeout(300000)
				.setStaleConnectionCheckEnabled(true).build();
		httppost.setConfig(defaultRequestConfig);
		return httppost;
	}
	
	
	/**
	 * 关闭http请求及返回
	 * 
	 * @param response
	 * @param httpclient
	 */
	private static void close(CloseableHttpResponse response, CloseableHttpClient httpclient) {
		if (response != null) {
			try {
				response.close();
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
		if (httpclient != null) {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
	}
	
	
	/**
	 * 发送get 请求访问
	 * 
	 * @param url
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static String get(String url) {
		String result = null;
		HttpEntity respEntity = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			
			HttpGet httpGet=createHttpGet(url);
			response = httpclient.execute(httpGet);
			if (200 != response.getStatusLine().getStatusCode()) {// 失败
				log.error("Receive http response:" + response.getStatusLine().getStatusCode());
				return null;
			}
			respEntity = response.getEntity();
			if (respEntity != null) {
				result = EntityUtils.toString(respEntity, "UTF-8");
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			//throw ErrorUtils.wrap(GenericError.REQUEST_ERROR, e);
		} finally {
			try {
				EntityUtils.consume(respEntity);
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
			close(response, httpclient);
		}
		return result;
	}
	 
	/**
	 * 创建GET请求
	 * 
	 * @param url
	 * @return
	 */
	private static HttpGet createHttpGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
		httpGet.addHeader("Connection", "Keep-Alive");  
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");  
		httpGet.addHeader("Cookie", "");  
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(300000)
				.setConnectTimeout(300000).setConnectionRequestTimeout(300000)
				.setStaleConnectionCheckEnabled(true).build();
		httpGet.setConfig(defaultRequestConfig);
		return httpGet;
	}
	
	
	/**
	 * httpget 带参数 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String httpGet(String url,Map<String, Object> params) throws IOException {
		String result = null;
		HttpEntity respEntity = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		
        try {
        	if (params != null && !params.isEmpty()) {
        		List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
        		for (Entry<String, Object> entry : params.entrySet()) {
        			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        		}
        		url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, "UTF-8"));
        	}
        	HttpGet httpGet = new HttpGet(url);
        	httpClient = HttpClients.createDefault();
			response = httpClient.execute(httpGet);
			// 获取响应信息
			if (200 != response.getStatusLine().getStatusCode()) {// 失败
				log.error("Receive http response:" + response.getStatusLine().getStatusCode());
				return null;
			}
			respEntity = response.getEntity();
			if (respEntity != null) {
				result = EntityUtils.toString(respEntity, "UTF-8");
			}
		}catch (Exception e) {
		log.error("httpGet异常",e);
		}finally {
			try {
				EntityUtils.consume(respEntity);
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
			close(response, httpClient);
		}
        return result;
	}
	public static void main(String[] args) throws  Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("erterpriseid", 138);
		map.put("enterprisecode", "YINENG");

			map.put("gmfnsrsbh", "421081188605484568");
			map.put("gmfmc", "陈金杰");
//		else{
//			map.put("gmfnsrsbh", electronicInvoice.getOrderInvoiceTitle().getDutyParagraphNo());
//			map.put("gmfmc", electronicInvoice.getOrderInvoiceTitle().getCompanyName());
//		}
		map.put("billno", "1942613347232878");
		map.put("kpr", "翌能");
		String result;
		String url = "http://test.base.scn.weilian.cn" + "/pubElectronicInvoice/add";
		result = HttpClientUtils.httpGet(url, map);
		System.out.println("-------------调用远程接口结束------------");
		JSONObject jsonReult = JSON.parseObject(result);
		System.out.println("-----------erp返回数据：" + jsonReult.toString());
	}
}

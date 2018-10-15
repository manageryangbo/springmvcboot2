package com.pseudo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.*;



/**
 * Created by Administrator on 2017/6/7.
 */
public class RequestFilter implements ContainerRequestFilter {

	private static Logger logger = LoggerFactory.getLogger(RequestFilter.class);

	// 白名单地址(无需验证),可考虑统一一个 /xxx/nota/xxx 这样的地址，中间含 /nota/ 的为白名单地址
	private String[] unAuthUris = { "/couponactivity/importvipfromexcel","/marketingcenter/batchimportactivitygoodsexcel" };// {};
	private String[] staticUris = { ".html", ".htm", ".js", ".txt", ".css", ".map", ".less", ".png", ".jpg", "*.gif",
			".font", ".eot", ".ttf", ".woff", ".woff2", ".pro", "svg", ".xlsx" };

	@Override
	public void filter(ContainerRequestContext req) throws IOException {

		// check(req);
		requestParser(req);

	}

	/*
	 * private void check(ContainerRequestContext req){
	 * LocalVarys.clear();//开始处理前清除本线程相关的变量 String uri = req.getUriInfo().getPath();
	 * if(uri==null){ return; } uri=uri.toLowerCase(); if(staticUris != null &&
	 * endsWith(uri,staticUris)){ return; } if(unAuthUris == null){ return; }else{
	 * if( contains(uri,unAuthUris) ){ return; } }
	 * 
	 * String ssid=req.getHeaderString("sessionId"); if(ssid==null){ ResponseBuilder
	 * rb = Response.status(Status.UNAUTHORIZED); Response rs=rb. build();
	 * req.abortWith(rs); return; }
	 * 
	 * SystemUserInfoT user = CacheUtils.get(SystemUserInfoT.class, ssid);
	 * if(user==null){ ResponseBuilder rb = Response.status(Status.UNAUTHORIZED);
	 * Response rs=rb. build(); req.abortWith(rs); }else{ LocalVarys.set("user",
	 * user);//接口处可直接通过 LocalVarys.get("user") 来获取登录用户对象 } }
	 */

	private boolean contains(String uri, String[] ls) {
		boolean b = false;
		for (String l : ls) {
			if (uri.contains(l.toLowerCase())) {
				b = true;
				break;
			}
		}
		return b;
	}

	private boolean endsWith(String uri, String[] ls) {
		boolean b = false;
		for (int i = 0; i < ls.length; i++) {
			if (uri.endsWith(ls[i])) {
				b = true;
				break;
			}
		}
		return b;
	}

	/**
	 * 请求解析
	 * 
	 * @param req
	 */
	private void requestParser(ContainerRequestContext req) {
		String method = req.getMethod();
		if (RequestMethod.POST.name().equals(method) || RequestMethod.PUT.name().equals(method)) {
			requestParserForPost(req);
		} 
		//else if (RequestMethod.GET.name().equals(method)) {
			// requestParserForGet(req)
		//}
	}

	/*
	 * get请求参数拦截
	 * 
	 * @param req
	 * @throws IOException
	 * 
	 *             private void requestParserForGet(ContainerRequestContext req)
	 *             throws IOException { // 是GET请求 PreMatchContainerRequestContext pr
	 *             = (PreMatchContainerRequestContext) req; HttpRequest httpRequest
	 *             = pr.getHttpRequest(); MultivaluedMap<String, String> parmMap =
	 *             httpRequest.getUri().getQueryParameters(); List<String> length =
	 *             parmMap.get("length"); length.clear(); length.add("6"); }
	 */

	/**
	 * post请求参数拦截
	 * 
	 * @param req
	 * @throws IOException
	 */
	private void requestParserForPost(ContainerRequestContext req) {
		String uri = req.getUriInfo().getPath();
		if (uri == null) {
			return;
		}
		uri = uri.toLowerCase();
		if (staticUris != null && endsWith(uri, staticUris)) {
			return;
		}
		if (contains(uri, unAuthUris)) {
			return;
		}


		StringBuilder sb = new StringBuilder();
		InputStream in = req.getEntityStream();
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			if (in != null) {
				inputStreamReader = new InputStreamReader(in);
				bufferedReader = new BufferedReader(inputStreamReader);
				String line = bufferedReader.readLine();
				while (line != null) {
					sb.append(line);
					line = bufferedReader.readLine();
				}
			}
			String msg = sb.toString();
			if (msg.indexOf("<") > 0 || msg.indexOf(">") > 0) { // &lt; &gt;
				msg = msg.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			}
			byte[] buf = msg.getBytes();
			ByteArrayInputStream inn = new ByteArrayInputStream(buf);
			req.setEntityStream(inn);

		} catch (IOException e) {
			req.setEntityStream(in);
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					logger.error(e1.getMessage(), e1);
				}
			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

	}

}

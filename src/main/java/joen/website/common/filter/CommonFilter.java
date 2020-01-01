package joen.website.common.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class CommonFilter implements Filter {
	Logger logger =  LoggerFactory.getLogger(getClass());
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		MultiReadHttpServletRequest httpServletRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);
		HttpServletResponse httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
		
		SecureRandom random = new SecureRandom();
		Long globalId = System.currentTimeMillis() + random.nextInt(10000);
		httpServletRequest.setAttribute("globalId", globalId);
		
		String params = getParams(httpServletRequest);
		
		logger.info("Request Log - uri:" + httpServletRequest.getRequestURI() 
		+ ", method: " + httpServletRequest.getMethod()
		+ ", parmas: " + params 
		+ ", x-auth: " + httpServletRequest.getHeader("X-Authorization")
		+ ", globalId: " + httpServletRequest.getAttribute("globalId"));
		
		long startTime = System.currentTimeMillis();
		chain.doFilter(httpServletRequest, response);
		long endTime = System.currentTimeMillis();
		long execTime = endTime - startTime;
		
		logger.info("Response Log - status:" + httpServletResponse.getStatus()
		+", globalId: " + httpServletRequest.getAttribute("globalId")
		+", executionTime: " + execTime + "ms");
	}
	
	private String getParams(MultiReadHttpServletRequest httpServletRequest) {
		String method = httpServletRequest.getMethod();
		String path = httpServletRequest.getRequestURI();
		String params = null;
		
		if(path.contains("file/upload")) {
			return null;
		}
		
		if("GET".equalsIgnoreCase(method)|| "DELETE".equalsIgnoreCase(method)) {
			Enumeration<String> paramNames = httpServletRequest.getParameterNames();
			JSONObject paramsJson = new JSONObject();
			
			while(paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				try {
					paramsJson.put(paramName, httpServletRequest.getParameter(method));
				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}else if("POST".equalsIgnoreCase(method)||"PUT".equalsIgnoreCase(method)) {
			params = readBody(httpServletRequest);
		}
		return params;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}
	
	private static String readBody(MultiReadHttpServletRequest httpServletRequest) {
		try {
			if(httpServletRequest.getInputStream() == null) {
				return null;
			}
			return StreamUtils.copyToString(httpServletRequest.getInputStream(),Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException(e);
		}
		
	}

}

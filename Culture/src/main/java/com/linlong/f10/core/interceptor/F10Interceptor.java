/**   
 * @Title: F10Interceptor.java
 * @Package com.f10.core.interceptor
 * @Description: 
 * @author  yangwenjian   
 * @date 2016年7月13日 上午1:22:39
 * @version V1.0   
 */

package com.linlong.f10.core.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.core.authentication.service.AuthService;

/**
 * @ClassName: F10Interceptor
 * @Description: 认证filter
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月13日 上午1:22:39
 * 
 */
public class F10Interceptor implements HandlerInterceptor {
	private static Logger LOGGER = Logger.getLogger(F10Interceptor.class);
	@Resource(name = "f10AuthService")
	private AuthService authService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String token = request.getParameter("tokenId");
		if(StringUtils.isBlank(token)){
			LOGGER.info("tokenId is required in the parameter.");
			response.setStatus(403);
			response.getWriter().append("tokenId is required in the parameter.");
			return false;
		}
		String device = request.getParameter("device");
		
		if (authService.validateToken(device, token) == true) {
			return true;
		} else {
			LOGGER.info("not authentication request");
			response.setStatus(403);
			response.getWriter().append("token is invalidate.");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}

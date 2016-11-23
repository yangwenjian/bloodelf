package com.linlong.f10.core.authentication.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linlong.f10.core.authentication.service.AuthService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	@Resource(name = "f10AuthService")
	private AuthService authService;

	/**
	 * Created on 2015年7月8日 Discription: [返回总流通股本信息]
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/expire")
	@ResponseBody
	public String queryDevindent(List<String> tokenIds,
			HttpServletRequest request, HttpServletResponse response) {
		authService.expire(tokenIds);
		return null;
	}

}

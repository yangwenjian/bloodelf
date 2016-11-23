package com.linlong.f10.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linlong.f10.dto.market.View;
import com.linlong.f10.view.service.ViewService;
import com.linlong.f10.view.service.ViewType;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/viewpoint")
public class ViewController {

	@Autowired
	private ViewService viewService;

	/**
	 * Created on 2015年7月8日 Discription: [返回巴菲特观点]
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/index")
	@ResponseBody
	public List<View> buffettView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "stockcode", required = true) String stockcode,
			@RequestParam(value = "price", required = false, defaultValue = "0") double price) {
		List<View> views = viewService.getViews(stockcode, price);
		return views;
	}

	@RequestMapping(value = "/test")
	@ResponseBody
	public View buffettViewTest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "code", required = true) Integer code,
			@RequestParam(value = "viewType", required = true) ViewType viewType) {
		View view = viewService.getViewsTest(code, viewType);
		return view;
	}
}

package com.linlong.f10.resource.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linlong.f10.resource.service.AnnouncementService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @Description 公司公告响应类
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class AnnouncementController {

	@Autowired
	private AnnouncementService annoucementService;

	/**
	 * 
	 * @Title: 公司公告
	 * @Description: 公司公告相应方法
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/gsgg/pc/{stockcode}")
	public String announcement(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		List<Map<String, Object>> announcements = annoucementService.queryComAnnouncements(stockcode);
		map.put("stockcode", stockcode);
		map.put("announcements", announcements);
		return "pc/stock/announce";
	}

}

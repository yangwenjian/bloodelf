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

import com.linlong.f10.resource.service.MediaReportService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class MediaReportController {

	@Autowired
	private MediaReportService mediaReportService;

	/**
	 * 
	 * @Title: gsgk
	 * @Description: 公司概况页面
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/mtbd/pc/{stockcode}")
	public String mediaReport(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		List<Map<String, Object>> reports = mediaReportService.queryMeidaReports(stockcode);
		map.put("reports", reports);
		return "pc/stock/report";
	}

}

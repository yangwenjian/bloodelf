package com.linlong.f10.stockindexdetail.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linlong.f10.stockindexdetail.service.SampleStatisticsService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * @Description: 样本统计页面
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/indexdetail")
public class SampleStatisticsController {

	@Autowired
	private SampleStatisticsService sampleStatisticsService;

	/**
	 * 
	 * @Title: sampleStatistics
	 * @Description: 样本统计响应
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/ybtj/{platform}/{indexcode}")
	public String sampleStatistics(@PathVariable("indexcode") String indexcode,
			@PathVariable("platform") String platform,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap map) {
		String token = request.getParameter("tokenId");
		map.addAttribute("tokenId", token);
		List<Map<String, Object>> sampleStatistics = sampleStatisticsService
				.querySampleStatistics(indexcode);
		map.put("indexcode", indexcode);
		map.put("sampleStatistics", sampleStatistics);
		if ("mobile".equals(platform)) {
			return "samplestat/mobile/index";
		} else {
			return "samplestat/pc/index";
		}
	}

}

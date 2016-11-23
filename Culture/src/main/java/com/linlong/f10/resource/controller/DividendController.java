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

import com.linlong.f10.resource.service.DividendService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @Description: 股本分红页面
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class DividendController {

	@Autowired
	private DividendService dividendService;

	/**
	 * 
	 * @Title: dividend
	 * @Description: 股本分红相应
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/gbfh/pc/{stockcode}")
	public String dividend(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		List<Map<String, Object>> shareCons = dividendService.queryShareCons(stockcode);
		map.addAttribute("shareCons", shareCons);
		List<Map<String, Object>> shareChanges = dividendService.queryShareChange(stockcode);
		map.addAttribute("shareChanges", shareChanges);
		List<Map<String, Object>> dividends = dividendService.queryDividend(stockcode);
		map.addAttribute("dividends", dividends);
		List<Map<String, Object>> allotments = dividendService.queryAllotment(stockcode);
		map.addAttribute("allotments", allotments);
		List<Map<String, Object>> additions = dividendService.queryAddition(stockcode);
		map.addAttribute("additions", additions);
		List<Map<String, Object>> convBonds = dividendService.queryConvBond(stockcode);
		map.addAttribute("convBonds", convBonds);
		return "pc/stock/dividend";
	}

}

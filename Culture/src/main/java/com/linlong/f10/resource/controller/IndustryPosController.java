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

import com.linlong.f10.dto.resource.IndustryCompany;
import com.linlong.f10.resource.service.IndustryPosService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class IndustryPosController {

	@Autowired
	private IndustryPosService industryPosService;

	/**
	 * 
	 * @Title: gsgk
	 * @Description: 行业地位
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/hydw/{platform}/{stockcode}")
	public String industryPos(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		Map<String, Object> indu = industryPosService.queryIndu(stockcode);
		map.put("indu", indu);
		List<Map<String, Object>> induPos = industryPosService.queryInduPos(stockcode);
		map.put("induPos", induPos);
		List<IndustryCompany> induCompanies = industryPosService.queryInduCom(stockcode);
		map.put("induCompanies", induCompanies);
		return "pc/stock/industry";
	}

}

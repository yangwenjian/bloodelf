package com.linlong.f10.resource.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linlong.f10.resource.service.ExecutiveService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @Description: 高管介绍页面
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class ExecutiveController {

	@Autowired
	private ExecutiveService executiveService;

	/**
	 * 
	 * @Title: executive
	 * @Description: 高管介绍相应
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/ggjs/pc/{stockcode}")
	public String executive(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		List<Map<String, Object>> execChanges = executiveService.queryExecChange(stockcode);
		map.addAttribute("execChanges", execChanges);
		List<Map<String, Object>> execDetials = executiveService.queryExecDetail(stockcode);
		map.addAttribute("execDetials", execDetials);
		List<Map<String, Object>> execChReasons = executiveService.queryExecChReason(stockcode);
		map.addAttribute("execChReasons", execChReasons);
		List<Map<String, Object>> execPTs = executiveService.queryExecPT(stockcode);
		map.addAttribute("execPTs", execPTs);
		if (CollectionUtils.isNotEmpty(execPTs)) {
			map.addAttribute("ptdate", execPTs.get(0).get("perioddate"));
		}
		List<Map<String, Object>> incetives = executiveService.queryStIncetive(stockcode);
		map.addAttribute("incetives", incetives);
		List<Map<String, Object>> execIntros = executiveService.queryExecIntro(stockcode);
		map.addAttribute("execIntros", execIntros);
		return "pc/stock/executive";
	}

}

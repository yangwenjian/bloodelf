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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linlong.f10.dto.resource.Operation;
import com.linlong.f10.resource.service.ComProfileService;
import com.linlong.f10.resource.service.IndustryPosService;
import com.linlong.f10.resource.service.OperationService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @Description 公司概况相应类别
 * @author: yangwenjian
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class ComProfileController {

	@Autowired
	private ComProfileService comProfileService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private IndustryPosService industryPosService;

	/**
	 * 
	 * @Title: gsgk
	 * @Description: 公司概况页面
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/gsgk/pc/{stockcode}")
	public String comProfile(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		Map<String, String> shareHolder = comProfileService.queryShareHolder(stockcode);
		map.put("shareHolder", shareHolder);
		String investKeyPoint = comProfileService.queryInvestKeyPoint(stockcode);
		map.put("investKeyPoint", investKeyPoint);
		Map<String, Object> pubOffer = comProfileService.queryPubOffer(stockcode);
		map.put("pubOffer", pubOffer);
		Map<String, Object> comProfile = comProfileService.queryComProfile(stockcode);
		map.put("comProfile", comProfile);
		Map<String, Object> emplInfo = comProfileService.queryEmplInfo(stockcode);
		map.put("emplInfo", emplInfo);
		List<Map<String, Object>> emplSitus = comProfileService.queryEmplSitu(stockcode);
		map.put("emplSitus", emplSitus);
		List<Map<String, Object>> firstShareHolders = comProfileService.queryFirstShareHolder(stockcode);
		map.put("fristShareHolders", firstShareHolders);
		return "pc/stock/profile";
	}

	/**
	 * 
	 * @Title: gsgk
	 * @Description: 公司概况页面
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/gsgk/mobile/{stockcode}")
	public String comProfileMobile(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			@RequestParam(value = "tokenId", required = true) String tokenId, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		map.put("tokenId", tokenId);
		
		return "mobile/stock/profile";
	}

	/**
	 * 
	 * @Title: gsgk
	 * @Description: 公司概况详细信息
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/profile/detail")
	@ResponseBody
	public Map<String, Object> comProfileDetail(@RequestParam("stockcode") String stockcode,
			HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		// map.put("stockcode", stockcode);
		// 公司概况
		Map<String, Object> comProfile = comProfileService.queryComProfile(stockcode);
		return comProfile;
	}

	/**
	 * 
	 * @Title: gsgk
	 * @Description: 主营构成详细
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/business/main")
	@ResponseBody
	public Operation comProfileBus(@RequestParam("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		// map.put("stockcode", stockcode);
		// 公司概况
		Operation mainBusStructs = operationService.queryMainBusinessBrief(stockcode);
		return mainBusStructs;
	}

	/**
	 * 
	 * @Title: gsgk
	 * @Description: 行业市场表现
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/industry")
	@ResponseBody
	public Map<String, Object> comProfileIndustry(@RequestParam("stockcode") String stockcode,
			HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		// map.put("stockcode", stockcode);
		Map<String, Object> comProfile = comProfileService.queryComProfile(stockcode);
		return comProfile;
	}

}

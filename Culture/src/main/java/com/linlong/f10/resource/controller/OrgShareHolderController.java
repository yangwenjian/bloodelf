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

import com.linlong.f10.dto.resource.OrgPosition;
import com.linlong.f10.resource.service.OrgShareHolderService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class OrgShareHolderController {

	@Autowired
	private OrgShareHolderService orgshareHolderService;

	/**
	 * Created on 2015年7月8日 Discription: [操盘必读页面]
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/jgcg/pc/{stockcode}")
	public String orgShareHolder(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		Map<String, Map<String, Object>> orgPositions = orgshareHolderService.queryOrgPosition(stockcode);
		map.addAttribute("orgPositions", orgPositions);
		List<Map<String, Object>> fundPostions = orgshareHolderService.queryFundPosition(stockcode);
		map.addAttribute("fundPostions", fundPostions);
		List<OrgPosition> orgHolders = orgshareHolderService.queryOrgHolders(stockcode);
		map.addAttribute("orgHolders", orgHolders);
		return "pc/stock/orgholder";

	}

}

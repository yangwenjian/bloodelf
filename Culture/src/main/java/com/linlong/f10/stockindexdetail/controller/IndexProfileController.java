package com.linlong.f10.stockindexdetail.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linlong.f10.stockindexdetail.service.IndexProfileService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * @Description: 指数概况页面
 * @author: yangwenjian
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/indexdetail")
public class IndexProfileController {

	@Autowired
	private IndexProfileService indexProfileService;
	// private static Logger LOGGER =
	// Logger.getLogger(ComProfileController.class);

	/**
	 * 
	 * @Title: indexProfile
	 * @Description: 指数概况页面
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/zsgk/{platform}/{indexcode}")
	public String indexProfile(@PathVariable("indexcode") String indexcode,
			@PathVariable("platform") String platform,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap map) {
		String token = request.getParameter("tokenId");
		map.addAttribute("tokenId", token);
		Map<String, Object> indexProfile = indexProfileService
				.queryIndexProfile(indexcode);
		map.put("indexcode", indexcode);
		map.put("indexProfile", indexProfile);
		if ("mobile".equals(platform)) {
			return "indexprofile/mobile/index";
		} else {
			return "indexprofile/pc/index";
		}
	}

}

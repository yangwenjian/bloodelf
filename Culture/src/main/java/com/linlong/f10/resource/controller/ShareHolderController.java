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

import com.linlong.f10.dto.resource.OrgPosition;
import com.linlong.f10.dto.resource.ShareHolderInfo;
import com.linlong.f10.resource.service.DividendService;
import com.linlong.f10.resource.service.ExecutiveService;
import com.linlong.f10.resource.service.OrgShareHolderService;
import com.linlong.f10.resource.service.ShareHolderService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class ShareHolderController {

	@Autowired
	private ShareHolderService shareHolderService;
	@Autowired
	private DividendService dividendService;
	@Autowired
	private ExecutiveService executiveService;
	@Autowired
	private OrgShareHolderService orgshareHolderService;

	/**
	 * Created on 2015年7月8日 Discription: 股东研究
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/gdyj/pc/{stockcode}")
	public String shareHolder(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		List<Map<String, Object>> holdSitus = shareHolderService.queryHoldSitu(stockcode);
		map.addAttribute("holdSitus", holdSitus);
		ShareHolderInfo shareHolder = shareHolderService.queryShareHolders(stockcode);
		map.addAttribute("shareHolder", shareHolder);
		List<ShareHolderInfo> hisShareHolders = shareHolderService.queryHisShareHolders(stockcode);
		map.addAttribute("hisShareHolders", hisShareHolders);
		return "pc/stock/holder";

	}

	/**
	 * Created on 2015年7月8日 Discription: 股本股东
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/gdyj/mobile/{stockcode}")
	public String shareHolderMobile(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			@RequestParam(value = "tokenId", required = true) String tokenId, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		map.put("tokenId", tokenId);
		// 股分构成
		List<Map<String, Object>> shareCons = dividendService.queryShareCons(stockcode);
		map.addAttribute("shareCons", shareCons);
		// 最新股东
		// 十大流通股东
		ShareHolderInfo shareHolder = shareHolderService.queryShareHolders(stockcode);
		map.addAttribute("shareHolder", shareHolder);
		// 高管持股明细
		List<Map<String, Object>> execDetials = executiveService.queryExecDetail(stockcode);
		map.addAttribute("execDetials", execDetials);
		// 机构持股
		Map<String, Map<String, Object>> orgPositions = orgshareHolderService.queryOrgPosition(stockcode);
		map.addAttribute("orgPositions", orgPositions);
		List<Map<String, Object>> fundPostions = orgshareHolderService.queryFundPositionBrief(stockcode);
		map.addAttribute("fundPostions", fundPostions);
		OrgPosition orgHolder = orgshareHolderService.queryOrgHoldersBrief(stockcode);
		map.addAttribute("orgHolder", orgHolder);
		return "mobile/stock/holder";

	}

	@RequestMapping(value = "/sharecon/detail")
	@ResponseBody
	public List<Map<String, Object>> shareCon(@RequestParam("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		// 股分构成
		List<Map<String, Object>> shareCons = dividendService.queryShareCons(stockcode);
		return shareCons;
	}

	@RequestMapping(value = "/shareholder/last")
	@ResponseBody
	public List<Map<String, Object>> lastShareHolder(@PathVariable("stockcode") String stockcode,
			HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		// 股分构成
		List<Map<String, Object>> shareCons = dividendService.queryShareCons(stockcode);
		return shareCons;
	}

	@RequestMapping(value = "/shareholder/flow")
	@ResponseBody
	public List<Map<String, Object>> flowShareHolder(@PathVariable("stockcode") String stockcode,
			HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		// 股分构成
		List<Map<String, Object>> shareCons = dividendService.queryShareCons(stockcode);
		return shareCons;
	}

	@RequestMapping(value = "/excution")
	@ResponseBody
	public List<Map<String, Object>> execDetials(@PathVariable("stockcode") String stockcode,
			HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		// 股分构成
		List<Map<String, Object>> shareCons = dividendService.queryShareCons(stockcode);
		return shareCons;
	}

	@RequestMapping(value = "/orgposition")
	@ResponseBody
	public Map<String, Map<String, Object>> orgPosition(@PathVariable("stockcode") String stockcode,
			HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		// 股分构成
		Map<String, Map<String, Object>> orgPositions = orgshareHolderService.queryOrgPosition(stockcode);
		return orgPositions;
	}

	@RequestMapping(value = "/fundposition")
	@ResponseBody
	public List<Map<String, Object>> fundPostion(@PathVariable("stockcode") String stockcode,
			HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		// 股分构成
		List<Map<String, Object>> shareCons = dividendService.queryShareCons(stockcode);
		return shareCons;
	}

	@RequestMapping(value = "/orgholder")
	@ResponseBody
	public List<Map<String, Object>> orgHolder(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		// 股分构成
		List<Map<String, Object>> shareCons = dividendService.queryShareCons(stockcode);
		return shareCons;
	}

}

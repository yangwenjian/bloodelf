package com.linlong.f10.resource.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linlong.f10.dto.resource.Fluctuation;
import com.linlong.f10.dto.resource.ShareChange;
import com.linlong.f10.dto.resource.ShareInfo;
import com.linlong.f10.dto.resource.StockIndicator;
import com.linlong.f10.resource.service.AnnouncementService;
import com.linlong.f10.resource.service.DividendService;
import com.linlong.f10.resource.service.FluctuationService;
import com.linlong.f10.resource.service.MediaReportService;
import com.linlong.f10.resource.service.ReviewService;
import com.linlong.f10.resource.service.StockRequireService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class StockRequireController {

	@Autowired
	private StockRequireService stockReqService;
	@Autowired
	private AnnouncementService annoucementService;
	@Autowired
	private DividendService dividendService;
	@Autowired
	private MediaReportService mediaReportService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FluctuationService fluctuationService;

	/**
	 * Created on 2015年7月8日 Discription: [操盘必读页面]
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/cpbd/pc/{stockcode}")
	public String stockRequire(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		List<String> sepSug = stockReqService.querySpeSug(stockcode);
		map.addAttribute("sepSuglist", sepSug);
		List<String> performance = stockReqService.queryPerformance(stockcode);
		map.addAttribute("performances", performance);
		List<StockIndicator> indicatorList = stockReqService.queryIndicator(stockcode);
		map.addAttribute("indicatorList", indicatorList);
		ShareInfo shareInfo = stockReqService.queryShare(stockcode);
		map.addAttribute("shareInfo", shareInfo);
		List<String> reviews = stockReqService.queryReview(stockcode);
		map.addAttribute("reviews", reviews);
		List<String> allotments = stockReqService.queryAllotment(stockcode);
		map.addAttribute("allotments", allotments);
		List<ShareChange> shareChanges = stockReqService.queryShareChange(stockcode);
		map.addAttribute("shareChanges", shareChanges);
		List<Map<String, String>> announcements = stockReqService.queryAnnouncement(stockcode);
		map.addAttribute("announcements", announcements);
		return "pc/stock/require";
	}

	@RequestMapping(value = "/cpbd/mobile/{stockcode}")
	public String stockRequireMobile(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			@RequestParam(value = "tokenId", required = true) String tokenId, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		map.put("tokenId", tokenId);
		// 公司公告标题
		List<Map<String, Object>> announceTitles = annoucementService.queryComAnnounceTitle(stockcode);
		map.addAttribute("announceTitles", announceTitles);
		// 股本分红表格
		List<Map<String, Object>> dividends = dividendService.queryDividend(stockcode);
		if (CollectionUtils.isNotEmpty(dividends) && dividends.size() > 5) {
			dividends = dividends.subList(0, 5);
		}
		map.addAttribute("dividends", dividends);
		// 媒体报道标题
		List<Map<String, Object>> reportTitles = mediaReportService.queryMeidaReportTitles(stockcode);
		map.addAttribute("reportTitles", reportTitles);
		// 市场品论标题
		List<Map<String, Object>> reviewTitles = reviewService.queryReviewTitles(stockcode);
		map.addAttribute("reviewTitles", reviewTitles);
		// 龙虎榜表格
		List<Fluctuation> fluctuations = fluctuationService.queryFluctuation(stockcode);
		if (CollectionUtils.isNotEmpty(fluctuations)) {
			map.put("fluct", fluctuations.get(0));
		}
		return "mobile/stock/require";
	}

	@RequestMapping(value = "/announce")
	@ResponseBody
	public Map<String, Object> stockReqAnnounce(@RequestParam("discId") String discId, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> announce = annoucementService.QueryComAnnounceById(discId);
		return announce;
	}

	@RequestMapping(value = "/report")
	@ResponseBody
	public Map<String, Object> stockReqReport(@RequestParam("guid") String guid, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> announce = mediaReportService.queryMeidaReportById(guid);
		return announce;
	}

	@RequestMapping(value = "/review")
	@ResponseBody
	public Map<String, Object> stockReqReview(@RequestParam("guid") String guid, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> announce = reviewService.queryReviewById(guid);
		return announce;
	}

	@RequestMapping(value = "/dragon")
	@ResponseBody
	public Fluctuation stockReqDragon(@RequestParam("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, String times) {
		List<Fluctuation> fluctuations = fluctuationService.queryFluctuation(stockcode);
		if (fluctuations != null && fluctuations.size() > 0) {
			if (StringUtils.isNotBlank(times)) {
				int num = Integer.valueOf(times);
				if (fluctuations.size() >= num) {
					return fluctuations.get(num - 1);
				}
			}
		}
		return null;
	}

}

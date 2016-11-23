package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.NewStockInfoService;

@Controller
@RequestMapping("/f10/index/newStockInfo")
public class NewStockInfoController {

	@Autowired
	NewStockInfoService newStockInfoService;

	@RequestMapping("/{clientType}")
	public ModelAndView dailyTips(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if ("pc".equals(clientType)) {
			view_model = "newStockInfo/pc/index";
		} else {
			view_model = "newStockInfo/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("todayNewStockInfo", newStockInfoService.getTodayNewStockInfo());
		mav.addObject("waitIssueStockInfo", newStockInfoService.getWaitIssueStockInfo());
		mav.addObject("waitListing", newStockInfoService.getWaitListing());
		mav.addObject("latestStock", newStockInfoService.getLatestStock());
		mav.addObject("newStockLocation", newStockInfoService.getNewStockLocation());
		return mav;
	}

}

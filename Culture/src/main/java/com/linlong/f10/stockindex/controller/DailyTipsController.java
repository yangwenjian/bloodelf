package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.DailyTipService;

@Controller
@RequestMapping("/f10/index/dailyTips")
public class DailyTipsController {
	
	@Autowired
	private DailyTipService dailyTipService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView dailyTips(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "dailyTip/pc/index";
		} else {
			view_model = "dailyTip/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("SH", dailyTipService.getSHTips());
		mav.addObject("SZ", dailyTipService.getSZTips());
		mav.addObject("Allotment", dailyTipService.getAllotmentTips());
		return mav;
	}
}

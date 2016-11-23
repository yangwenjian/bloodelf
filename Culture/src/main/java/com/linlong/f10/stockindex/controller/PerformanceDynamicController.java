package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.PerformanceDynamicService;

/**
 * 业绩动态
 * @author linlong
 *
 */
@Controller
@RequestMapping("/f10/index/performanceDynamic")
public class PerformanceDynamicController {

	@Autowired
	private PerformanceDynamicService performanceDynamicService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView dailyTips(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if ("pc".equals(clientType)) {
			view_model = "performanceDynamic/pc/index";
		} else {
			view_model = "performanceDynamic/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("earnings", performanceDynamicService.getEarnings());
		mav.addObject("SZEarningsPlus", performanceDynamicService.getSZEarningsPlus());
		mav.addObject("SZEarningsSubtract", performanceDynamicService.getSZEarningsSubtract());
		mav.addObject("SHEarningsPlus", performanceDynamicService.getSHEarningsPlus());
		mav.addObject("SHEarningsSubtract", performanceDynamicService.getSHEarningsSubtract());
		return mav;
	}
}

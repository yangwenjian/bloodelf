package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.SingleStockInfoService;

@Controller
@RequestMapping("/f10/index/singleStockInfo")
public class SingleStockInfoController {

	@Autowired
	private SingleStockInfoService singleStockInfoService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView getSingleStockInfo(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "singleStockInfo/pc/index";
		} else {
			view_model = "singleStockInfo/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("news", singleStockInfoService.getNews());
		return mav;
	}
	
}

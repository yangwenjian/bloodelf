package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.StockIndexFuturesService;

@Controller
@RequestMapping("/f10/index/stockIndexFutures")
public class StockIndexFuturesController {

	@Autowired
	private StockIndexFuturesService stockIndexFuturesService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView getStockIndexFutures(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "stockIndexFutures/pc/index";
		} else {
			view_model = "stockIndexFutures/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("news", stockIndexFuturesService.getNews());
		return mav;
	}
}

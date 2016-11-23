package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.BondObserveService;

@Controller
@RequestMapping("/f10/index/bondObserve")
public class BondObserveController {

	@Autowired
	private BondObserveService bondObserveService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView getstockIndexFutures(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "bondObserve/pc/index";
		} else {
			view_model = "bondObserve/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("news", bondObserveService.getNews());
		return mav;
	}
}

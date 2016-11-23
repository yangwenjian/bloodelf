package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.FundObserveService;

@Controller
@RequestMapping("/f10/index/fundobserve")
public class FundObserveController {

	@Autowired
	FundObserveService fundObserveService;

	@RequestMapping("/{clientType}")
	public ModelAndView news(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if ("pc".equals(clientType)) {
			view_model = "fundobserve/pc/index";
		} else {
			view_model = "fundobserve/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("marketInfos",
				fundObserveService.getFundMarketDynamicInfo());
		mav.addObject("researchInfos", fundObserveService.getFundResearchInfo());
		mav.addObject("fundInfos", fundObserveService.getFundDynamicInfo());
		return mav;
	}
}

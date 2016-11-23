package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.ThirdBoardService;

@Controller
@RequestMapping("/f10/index/thirdboard")
public class ThirdBoardController {

	@Autowired
	ThirdBoardService thirdBoardService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView news(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "thirdboard/pc/index";
		} else {
			view_model = "thirdboard/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("market", thirdBoardService.getMarketStastistics());
		mav.addObject("reviewTitles", thirdBoardService.getReviewTitle());
		mav.addObject("reviewContents", thirdBoardService.getReviewContent());
		return mav;
	}
}

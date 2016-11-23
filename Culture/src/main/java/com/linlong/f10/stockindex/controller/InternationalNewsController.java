package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.InternationalNewsService;

@Controller
@RequestMapping("/f10/index/internationalNews")
public class InternationalNewsController {

	@Autowired
	private InternationalNewsService internationalNewsService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView getInternationalNews(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "internationalNews/pc/index";
		} else {
			view_model = "internationalNews/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("globalIndex", internationalNewsService.getGlobalIndex());
		mav.addObject("internationalNews", internationalNewsService.getInternationalNews());
		return mav;
	}
	
}

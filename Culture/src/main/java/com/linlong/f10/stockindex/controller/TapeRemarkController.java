package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.TapeRemarkService;

@Controller
@RequestMapping("/f10/index/tapeRemark")
public class TapeRemarkController {

	@Autowired
	private TapeRemarkService tapeRemarkService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView getBruntTrend(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "tapeRemark/pc/index";
		} else {
			view_model = "tapeRemark/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("news", tapeRemarkService.getNews());
		return mav;
	}
	
}

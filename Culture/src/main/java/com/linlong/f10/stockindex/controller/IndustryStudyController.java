package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.IndustryStudyService;

@Controller
@RequestMapping("/f10/index/industryStudy")
public class IndustryStudyController {

	@Autowired
	private IndustryStudyService industryStudyService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView getIndustryStudy(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "industryStudy/pc/index";
		} else {
			view_model = "industryStudy/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("news", industryStudyService.getNews());
		return mav;
	}
}

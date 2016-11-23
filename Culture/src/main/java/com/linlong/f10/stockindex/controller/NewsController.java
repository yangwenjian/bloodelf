package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.NewsService;

@Controller
@RequestMapping("/f10/index/news")
public class NewsController {

	@Autowired
	NewsService newsService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView news(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "news/pc/index";
		} else {
			view_model = "news/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("todayNews", newsService.getTodayNews());
		mav.addObject("yestdayNews", newsService.getYestdayNews());
		mav.addObject("todayFocus", newsService.getTodayFocus());
		return mav;
	}
}

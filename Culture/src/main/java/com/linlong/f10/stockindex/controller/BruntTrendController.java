package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.BruntTrendService;

@Controller
@RequestMapping("/f10/index/bruntTrend")
public class BruntTrendController {

	@Autowired
	private BruntTrendService bruntTrendService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView getBruntTrend(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "bruntTrend/pc/index";
		} else {
			view_model = "bruntTrend/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("SH", bruntTrendService.getSHBruntTrend());
		mav.addObject("SZ", bruntTrendService.getSZBruntTrend());
		return mav;
	}
	
}

package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.ManyDimensionsStatisticsService;

@Controller
@RequestMapping("/f10/index/manyDimensionsStatistics")
public class ManyDimensionsStatisticsController {

	@Autowired
	private ManyDimensionsStatisticsService manyDimensionsStatisticsService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView getstockIndexFutures(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "manyDimensionsStatistics/pc/index";
		} else {
			view_model = "manyDimensionsStatistics/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("dividendScheme", manyDimensionsStatisticsService.getDividendScheme());
		mav.addObject("manyDimensionsStatistics", manyDimensionsStatisticsService.getManyDimensionsStatistics());
		return mav;
	}
}

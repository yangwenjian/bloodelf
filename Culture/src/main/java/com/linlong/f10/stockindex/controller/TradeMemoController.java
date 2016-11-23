package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.TradeMemoService;

@Controller
@RequestMapping("/f10/index/tradeMemo")
public class TradeMemoController {
	
	@Autowired
	TradeMemoService tradeMemoService;
	
	@RequestMapping("/{clientType}")
	public ModelAndView tradeMemo(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if("pc".equals(clientType)) {
			view_model = "tradeMemo/pc/index";
		} else {
			view_model = "tradeMemo/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("SH", tradeMemoService.getSHMemo());
		mav.addObject("SZ", tradeMemoService.getSZMemo());
		return mav;
	}
	
}

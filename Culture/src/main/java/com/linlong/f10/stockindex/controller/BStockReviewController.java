package com.linlong.f10.stockindex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.stockindex.service.BStockReviewService;

@Controller
@RequestMapping("/f10/index/bstock")
public class BStockReviewController {

	@Autowired
	private BStockReviewService bstockReviewService;

	@RequestMapping("/{clientType}")
	public ModelAndView news(@PathVariable("clientType") String clientType) {
		String view_model = null;
		if ("pc".equals(clientType)) {
			view_model = "bstock/pc/index";
		} else {
			view_model = "bstock/mobile/index";
		}
		ModelAndView mav = new ModelAndView(view_model);
		mav.addObject("tapeReviewTitles",
				bstockReviewService.getTapeReviewTitle());
		mav.addObject("tapeReviewInfos",
				bstockReviewService.getTapeReviewInfoc());
		mav.addObject("individualReviews",
				bstockReviewService.getIndividualReview());
		return mav;
	}
}

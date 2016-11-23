package com.linlong.f10.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.resource.service.ComProfileService;

@RequestMapping("/template")
@Controller
public class CommonController {

	@Autowired
	private ComProfileService comProfileService;

	@RequestMapping("/index/pc")
	public ModelAndView indexTemplatePc(
			@RequestParam(value = "tokenId", required = true) String tokenId) {
		ModelAndView mav = new ModelAndView("common/index_pc");
		mav.addObject("tokenId", tokenId);
		return mav;
	}

	@RequestMapping("/index/mobile")
	public ModelAndView indexTemplateMobile(
			@RequestParam(value = "tokenId", required = true) String tokenId) {
		ModelAndView mav = new ModelAndView("common/index_mobile");
		mav.addObject("tokenId", tokenId);
		return mav;
	}

	@RequestMapping("/stock/pc")
	public ModelAndView stockTemplatePc(
			@RequestParam(value = "tokenId", required = true) String tokenId,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("common/stock_pc");
		String stockcode = (String) request.getAttribute("stockcode");
		String stockname = comProfileService.queryStockName(stockcode);
		mav.addObject("stockname", stockname);
		mav.addObject("tokenId", tokenId);
		return mav;
	}

	@RequestMapping("/stock/mobile")
	public ModelAndView stockTemplateMobile(
			@RequestParam(value = "tokenId", required = true) String tokenId,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("common/stock_mobile");
		mav.addObject("tokenId", tokenId);
		String stockcode = (String) request.getAttribute("stockcode");
		String stockname = comProfileService.queryStockName(stockcode);
		mav.addObject("stockname", stockname);
		return mav;
	}

	@RequestMapping("/indexdetail/pc")
	public ModelAndView indexdetailTemplatePc(
			@RequestParam(value = "tokenId", required = true) String tokenId) {
		ModelAndView mav = new ModelAndView("common/indexdetail_pc");
		mav.addObject("tokenId", tokenId);
		return mav;
	}

	@RequestMapping("/indexdetail/mobile")
	public ModelAndView indexdetailTemplateMobile(
			@RequestParam(value = "tokenId", required = true) String tokenId) {
		ModelAndView mav = new ModelAndView("common/indexdetail_mobile");
		mav.addObject("tokenId", tokenId);
		return mav;
	}
}

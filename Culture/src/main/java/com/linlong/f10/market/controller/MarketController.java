package com.linlong.f10.market.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linlong.f10.market.service.ContentType;
import com.linlong.f10.market.service.FileService;
import com.linlong.f10.market.service.MarketService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/market")
public class MarketController {

	@Resource(name = "etlService")
	private MarketService marketService;

	private static Logger LOGGER = Logger.getLogger(MarketController.class);

	/**
	 * Created on 2015年7月8日 Discription: [返回总流通股本信息]
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/category")
	@ResponseBody
	public String queryCategory(HttpServletRequest request, HttpServletResponse response) {
		// 通过codes生成MD5+Base64编码后值，即文件特征值
		response.setContentType("text/plain;charset=UTF-8");
		String fileName = marketService.queryMarketFile(ContentType.CATEGORY);
		String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/" + FileService.DOWNLOADDIR + "/" + fileName;
		return fileUrl;
	}

	/**
	 * Created on 2015年7月8日 Discription: [返回总流通股本信息]
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/floatshare", method = RequestMethod.POST, headers = { "content-type=application/json;charset=utf-8" })
	@ResponseBody
	public String queryMarket(@RequestBody List<String> codes, HttpServletRequest request, HttpServletResponse response) {
		// 通过codes生成MD5+Base64编码后值，即文件特征值
		response.setContentType("text/plain;charset=UTF-8");
		String fileName = marketService.queryFloatShareFile(codes);
		String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/" + FileService.DOWNLOADDIR + "/" + fileName;

		return fileUrl;
	}

	/**
	 * Created on 2015年7月8日 Discription: [返回总流通股本信息]
	 * 
	 * @return String url to download result in file
	 * @author: yangwenjian
	 * @update: 2016年6月24日
	 */
	@RequestMapping(value = "/allfloatshare")
	@ResponseBody
	public String queryMarketWithStockCodes(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		// response.set
		String market = request.getParameter("market");
		String fileName = null;
		if ("sz".equalsIgnoreCase(market)) {
			fileName = marketService.queryMarketFile(ContentType.FLOATSHARE_SZ);
		} else if ("sh".equalsIgnoreCase(market)) {
			fileName = marketService.queryMarketFile(ContentType.FLOATSHARE_SH);
		} else {
			response.setStatus(400);
			return "证券交易所参数错误，请指定请求参数market=sh或者sz！";
		}
		String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/" + FileService.DOWNLOADDIR + "/" + fileName;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("return file url: " + fileUrl);
		}
		return fileUrl;

	}

	// /**
	// *
	// * @Title: queryProvince
	// * @Description: 返回省代码
	// * @param
	// * @return String 返回类型
	// */
	// @RequestMapping(value = "/province")
	// @ResponseBody
	// public String queryProvince(HttpServletRequest request,
	// HttpServletResponse response) {
	// String fileName = marketService.queryMarketFile(FileType.PROVINCE);
	// String fileUrl = domain + request.getContextPath() + "/" +
	// FileService.DOWNLOADDIR + "/" + fileName;
	// return fileUrl;
	// }

	/**
	 * 
	 * @Title: queryProvinceStockCode @Description: 返回各个省的股票代码 @param @param
	 *         request @param @param reponse @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	@RequestMapping(value = "/provincestockcode")
	@ResponseBody
	public String queryProvinceStockCode(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		String fileName = marketService.queryMarketFile(ContentType.PROVINCESTCODE);
		String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/" + FileService.DOWNLOADDIR + "/" + fileName;
		return fileUrl;
	}

	// /**
	// *
	// * @Title: queryIndustry
	// * @Description: 返回广度遍历的属性行业代码
	// * @return String 文件下载地址
	// */
	// @RequestMapping(value = "/industry")
	// @ResponseBody
	// public String queryIndustry(HttpServletRequest request,
	// HttpServletResponse response) {
	// String fileName = marketService.queryMarketFile(FileType.INDUSTRY);
	// String fileUrl = domain + request.getContextPath() + "/" +
	// FileService.DOWNLOADDIR + "/" + fileName;
	// return fileUrl;
	// }

	/**
	 * 
	 * @Title: queryProvinceStockCode @Description: 返回各个省的股票代码 @param @param
	 *         request @param @param reponse @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	@RequestMapping(value = "/industrystockcode")
	@ResponseBody
	public String queryIndustryStockCode(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		String fileName = marketService.queryMarketFile(ContentType.INDUSTRYSTCODE);
		String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/" + FileService.DOWNLOADDIR + "/" + fileName;
		return fileUrl;
	}

	/**
	 * 
	 * @Title: warrantIndex
	 * @Description:
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/dividend")
	@ResponseBody
	public String warrantIndex(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		String fileName = marketService.queryMarketFile(ContentType.DIVIDEND);
		String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/" + FileService.DOWNLOADDIR + "/" + fileName;
		return fileUrl;
	}

	/**
	 * 
	 * @Title: warrantIndex
	 * @Description:
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/dividend/inc")
	@ResponseBody
	public String warrantIncrement(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		String fileName = marketService.queryMarketFile(ContentType.DIVIDENDINC);
		String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/" + FileService.DOWNLOADDIR + "/" + fileName;
		return fileUrl;
	}

}

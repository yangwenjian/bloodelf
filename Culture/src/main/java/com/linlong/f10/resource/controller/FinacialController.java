package com.linlong.f10.resource.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.linlong.f10.resource.service.FinancialService;

/**
 * Created on 2015年7月8日 Title: 麟龙大平台_[CMS]_[菜单管理] Description: [描述该类功能介绍]
 * Copyright: Copyright (c) 2015 Company: 麟龙科技股份有限公司 Department: 研发部 财务分析页面
 * 
 * @author: nisicong
 * @version: 1.0
 */
@Controller
@RequestMapping("/f10/stock")
public class FinacialController {

	@Autowired
	private FinancialService financialService;

	/**
	 * 
	 * @Title: finacial
	 * @Description: 财务情况页面相应
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/cwfx/pc/{stockcode}")
	public String financial(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		List<Map<String, Object>> enddates = financialService.queryEnddate(stockcode);
		map.put("enddates", enddates);
		List<Map<String, Object>> investments = financialService.queryInvestment(stockcode);
		map.put("investments", investments);
		List<Map<String, Object>> debts = financialService.queryDebtPaying(stockcode);
		map.put("debts", debts);
		List<Map<String, Object>> developings = financialService.queryDeveloping(stockcode);
		map.put("developings", developings);
		List<Map<String, Object>> capStructs = financialService.queryCapStruct(stockcode);
		map.put("capStructs", capStructs);
		List<Map<String, Object>> opeeffs = financialService.queryOpeEfficiency(stockcode);
		map.put("opeeffs", opeeffs);
		List<Map<String, Object>> profits = financialService.queryProfit(stockcode);
		map.put("profits", profits);
		List<Map<String, Object>> cashFlows = financialService.queryCashFlow(stockcode);
		map.put("cashFlows", cashFlows);
		List<Map<String, Object>> risks = financialService.queryRisk(stockcode);
		if (CollectionUtils.isEmpty(risks)) {
			risks = new ArrayList<Map<String, Object>>(5);
		}
		map.put("risks", risks);
		List<Map<String, Object>> audits = financialService.queryAudit(stockcode);
		map.put("audits", audits);
		List<Map<String, Object>> capDebtTables = financialService.queryCapDebtTable(stockcode);
		map.put("capDebtTables", capDebtTables);
		List<Map<String, Object>> profitTables = financialService.queryProfitTable(stockcode);
		map.put("profitTables", profitTables);
		List<Map<String, Object>> cashFlowTables = financialService.queryCashFlowTable(stockcode);
		map.put("cashFlowTables", cashFlowTables);
		return "pc/stock/finantial";
	}

	/**
	 * 
	 * @Title: finacial
	 * @Description: 财务情况页面相应
	 * @param
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/cwfx/mobile/{stockcode}")
	public String financialMobile(@PathVariable("stockcode") String stockcode, HttpServletRequest request,
			@RequestParam(value = "tokenId", required = true) String tokenId, HttpServletResponse response, ModelMap map) {
		map.put("stockcode", stockcode);
		map.put("tokenId", tokenId);
		// 主营指标
		List<Map<String, Object>> enddates = financialService.queryEnddate(stockcode);
		map.put("enddates", enddates);
		List<Map<String, Object>> investments = financialService.queryInvestment(stockcode);
		map.put("investments", investments);
		List<Map<String, Object>> debts = financialService.queryDebtPaying(stockcode);
		map.put("debts", debts);
		List<Map<String, Object>> developings = financialService.queryDeveloping(stockcode);
		map.put("developings", developings);
		List<Map<String, Object>> capStructs = financialService.queryCapStruct(stockcode);
		map.put("capStructs", capStructs);
		List<Map<String, Object>> opeeffs = financialService.queryOpeEfficiency(stockcode);
		map.put("opeeffs", opeeffs);
		List<Map<String, Object>> profits = financialService.queryProfit(stockcode);
		map.put("profits", profits);
		List<Map<String, Object>> cashFlows = financialService.queryCashFlow(stockcode);
		map.put("cashFlows", cashFlows);
		List<Map<String, Object>> risks = financialService.queryRisk(stockcode);
		if (CollectionUtils.isEmpty(risks)) {
			risks = new ArrayList<Map<String, Object>>(5);
		}
		map.put("risks", risks);
		List<Map<String, Object>> audits = financialService.queryAudit(stockcode);
		map.put("audits", audits);
		// 资产负债表
		List<Map<String, Object>> capDebtTables = financialService.queryCapDebtTable(stockcode);
		map.put("capDebtTables", capDebtTables);
		// 利润分配表
		List<Map<String, Object>> profitTables = financialService.queryProfitTable(stockcode);
		map.put("profitTables", profitTables);
		// 现金流量表
		List<Map<String, Object>> cashFlowTables = financialService.queryCashFlowTable(stockcode);
		map.put("cashFlowTables", cashFlowTables);
		return "mobile/stock/financial";
	}

	// @RequestMapping(value = "/capdebt")
	// @ResponseBody
	// public List<Map<String, Object>> capDebt(@PathVariable("stockcode")
	// String stockcode, HttpServletRequest request,
	// HttpServletResponse response, ModelMap map) {
	// List<Map<String, Object>> capDebtTables =
	// financialService.queryCapDebtTable(stockcode);
	// return capDebtTables;
	// }
	//
	// @RequestMapping(value = "/profit")
	// @ResponseBody
	// public List<Map<String, Object>> profit(@PathVariable("stockcode") String
	// stockcode, HttpServletRequest request,
	// HttpServletResponse response, ModelMap map) {
	// List<Map<String, Object>> profitTables =
	// financialService.queryProfitTable(stockcode);
	// return profitTables;
	// }
	//
	// @RequestMapping(value = "/cashflow")
	// @ResponseBody
	// public List<Map<String, Object>> cashFlow(@PathVariable("stockcode")
	// String stockcode, HttpServletRequest request,
	// HttpServletResponse response, ModelMap map) {
	// List<Map<String, Object>> cashFlowTables =
	// financialService.queryCashFlowTable(stockcode);
	// return cashFlowTables;
	// }

}

/**   
 * @Title: GeniusFinancialService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:21:08
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.FinancialDAO;
import com.linlong.f10.resource.geniusf10dao.FinancialF10DAO;
import com.linlong.f10.resource.service.FinancialService;

/**
 * @ClassName: GeniusFinancialService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:21:08
 * 
 */
@Service
public class GeniusFinancialService implements FinancialService {

	@Autowired
	private FinancialDAO financialDAO;
	@Autowired
	FinancialF10DAO financialf10DAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#selectEnddate(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryEnddate\")")
	public List<Map<String, Object>> queryEnddate(String stockcode) {
		return financialDAO.selectEnddate(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryInvestment(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryInvestment\")")
	public List<Map<String, Object>> queryInvestment(String stockcode) {
		return financialf10DAO.selectInvestment(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryDebtPaying(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryDebtPaying\")")
	public List<Map<String, Object>> queryDebtPaying(String stockcode) {
		return financialDAO.selectDebtPaying(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryDeveloping(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryDeveloping\")")
	public List<Map<String, Object>> queryDeveloping(String stockcode) {
		return financialDAO.selectDeveloping(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryOpeEfficiency(
	 * java.lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryOpeEfficiency\")")
	public List<Map<String, Object>> queryOpeEfficiency(String stockcode) {
		return financialDAO.selectOpeEfficiency(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryProfite(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryProfit\")")
	public List<Map<String, Object>> queryProfit(String stockcode) {
		return financialDAO.selectProfite(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryCashFlow(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryCashFlow\")")
	public List<Map<String, Object>> queryCashFlow(String stockcode) {
		return financialDAO.selectCashFlow(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryRisk(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryRisk\")")
	public List<Map<String, Object>> queryRisk(String stockcode) {
		List<Map<String, Object>> risks = financialDAO.selectRisk(stockcode);
		return risks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryAudit(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryAudit\")")
	public List<Map<String, Object>> queryAudit(String stockcode) {
		return financialDAO.selectAudit(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryCapDebtTable(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryCapDebtTable\")")
	public List<Map<String, Object>> queryCapDebtTable(String stockcode) {
		return financialDAO.selectCapDebtTable(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryProfitTable(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryProfitTable\")")
	public List<Map<String, Object>> queryProfitTable(String stockcode) {
		return financialDAO.selectProfitTable(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryCashFlowTable(
	 * java.lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryCashFlowTable\")")
	public List<Map<String, Object>> queryCashFlowTable(String stockcode) {
		return financialDAO.selectCashFlowTable(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.FinancialService#queryCapStruct(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryCapStruct\")")
	public List<Map<String, Object>> queryCapStruct(String stockcode) {
		return financialDAO.selectCapStruct(stockcode);
	}

}

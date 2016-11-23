/**   
 * @Title: GeniusIndustryPosService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:23:59
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.dto.resource.IndustryCompany;
import com.linlong.f10.resource.geniusdao.IndustryPosDAO;
import com.linlong.f10.resource.service.IndustryPosService;

/**
 * @ClassName: GeniusIndustryPosService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:23:59
 * 
 */
@Service
public class GeniusIndustryPosService implements IndustryPosService {

	@Autowired
	private IndustryPosDAO industryPosDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.IndustryPosService#queryIndu(java.lang
	 * .String)
	 */
	@Override
	public Map<String, Object> queryIndu(String stockcode) {
		Map<String, Object> indu = industryPosDAO.selectIndu(stockcode);
		return indu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.IndustryPosService#queryInduPos(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryInduPos\")")
	public List<Map<String, Object>> queryInduPos(String stockcode) {
		List<Map<String, Object>> induPos = industryPosDAO
				.selectInduPos(stockcode);
		return induPos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.IndustryPosService#queryInduCom(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryInduCom\")")
	public List<IndustryCompany> queryInduCom(String stockcode) {
		List<Date> enddates = industryPosDAO.selectEnddate(stockcode);
		List<IndustryCompany> induCompanies = new ArrayList<IndustryCompany>();
		for (Date enddate : enddates) {
			IndustryCompany induCompany = new IndustryCompany();
			induCompany.setEnddate(enddate);
			List<Map<String, Object>> InduComs = industryPosDAO.selectInduCom(
					stockcode, enddate);
			induCompany.setComList(InduComs);
			Map<String, Object> epspRank = industryPosDAO.selectEpspRank(
					stockcode, enddate);
			if (epspRank != null && epspRank.get("RANK") != null) {
				induCompany.setCompanyName((String)epspRank.get("STOCKSNAME"));
				induCompany.setEpspRank((Double) epspRank.get("RANK"));
				Map<String, Object> incjRank = industryPosDAO.selectIncjRank(
						stockcode, enddate);
				induCompany.setIncjRank(incjRank != null ? (Double) incjRank
						.get("RANK") : null);
				Map<String, Object> roedRank = industryPosDAO.selectRoedRank(
						stockcode, enddate);
				induCompany.setRoedRank(roedRank != null ? (Double) roedRank
						.get("RANK") : null);
				Map<String, Object> bpsnedRank = industryPosDAO
						.selectBpsnedRank(stockcode, enddate);
				induCompany
						.setBpsnedRank(bpsnedRank != null ? (Double) bpsnedRank
								.get("RANK") : null);
				Map<String, Object> psnetvalRank = industryPosDAO
						.selectPsnetvalRank(stockcode, enddate);
				induCompany
						.setPsnetvalRank(psnetvalRank != null ? (Double) psnetvalRank
								.get("RANK") : null);
				Map<String, Object> inciRank = industryPosDAO.selectInciRank(
						stockcode, enddate);
				induCompany.setInciRank(inciRank != null ? (Double) inciRank
						.get("RANK") : null);
				Map<String, Object> selrintRank = industryPosDAO
						.selectSelrintRank(stockcode, enddate);
				induCompany
						.setSelrintRank(selrintRank != null ? (Double) selrintRank
								.get("RANK") : null);
				Map<String, Object> baloRank = industryPosDAO.selectBaloRank(
						stockcode, enddate);
				induCompany.setBaloRank(baloRank != null ? (Double) baloRank
						.get("RANK") : null);
				Map<String, Object> caplabRank = industryPosDAO
						.selectCaplabRank(stockcode, enddate);
				induCompany
						.setCaplabRank(caplabRank != null ? (Double) caplabRank
								.get("RANK") : null);
				Map<String, Object> opetaiRank = industryPosDAO
						.selectOpetaiRank(stockcode, enddate);
				induCompany
						.setOpetaiRank(opetaiRank != null ? (Double) opetaiRank
								.get("RANK") : null);

			}
			induCompanies.add(induCompany);
		}
		return induCompanies;
	}

}

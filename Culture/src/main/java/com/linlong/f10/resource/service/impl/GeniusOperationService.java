/**   
 * @Title: GeniusOperationService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:26:51
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.dto.resource.Operation;
import com.linlong.f10.resource.geniusdao.OperationDAO;
import com.linlong.f10.resource.service.OperationService;

/**
 * @ClassName: GeniusOperationService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:26:51
 * 
 */
@Service
public class GeniusOperationService implements OperationService {

	@Autowired
	private OperationDAO operationDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OperationService#queryBusiness(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryBusiness\")")
	public Map<String, Object> queryBusiness(String stockcode) {
		Map<String, Object> business = operationDAO.selectBusiness(stockcode);
		return business;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OperationService#queryBusPerioddate(
	 * java.lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryMainBusiness\")")
	public List<Operation> queryMainBusiness(String stockcode) {
		List<Operation> list = new ArrayList<Operation>();
		List<Date> enddates = operationDAO.selectBusPerioddate(stockcode);
		for (Date enddate : enddates) {
			Operation operation = new Operation();
			operation.setEnddate(enddate);
			List<Map<String, Object>> mainBusiness = operationDAO.selectMainBusiness(stockcode, enddate);
			operation.setInfo(mainBusiness);
			list.add(operation);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OperationService#queryBusPerioddate(
	 * java.lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryMainBusiness\")")
	public Operation queryMainBusinessBrief(String stockcode) {
		Operation operation = new Operation();
		List<Date> enddates = operationDAO.selectBusPerioddate(stockcode);
		if (CollectionUtils.isNotEmpty(enddates)) {
			Date enddate = enddates.get(0);
			operation.setEnddate(enddate);
			List<Map<String, Object>> mainBusiness = operationDAO.selectMainBusiness(stockcode, enddate);
			operation.setInfo(mainBusiness);
			operation.setEnddate(enddate);
		}
		return operation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OperationService#querySecInfo(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"querySecInfo\")")
	public List<Operation> querySecInfo(String stockcode) {
		List<Operation> list = new ArrayList<Operation>();
		List<Date> enddates = operationDAO.selectSecPerioddate(stockcode);
		for (Date enddate : enddates) {
			Operation operation = new Operation();
			operation.setEnddate(enddate);
			List<Map<String, Object>> securitys = operationDAO.selectSecInfo(stockcode, enddate);
			operation.setInfo(securitys);
			list.add(operation);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OperationService#queryRaiseInfo(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryRaiseInfo\")")
	public List<Map<String, Object>> queryRaiseInfo(String stockcode) {
		List<Map<String, Object>> raiseInfos = operationDAO.selectRaiseInfo(stockcode);
		return raiseInfos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OperationService#queryProject(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryProject\")")
	public List<Map<String, Object>> queryProject(String stockcode) {
		List<Map<String, Object>> projectsrcs = operationDAO.selectProjectsrc(stockcode);
		// 遍历项目来源列表，加入项目列表到map中，key为'projectList'
		for (Map<String, Object> projectsrc : projectsrcs) {
			Integer seq = (Integer) projectsrc.get("seq");
			List<Map<String, Object>> projects = operationDAO.selectProjectList(stockcode, seq);
			// 遍历项目列表，查询每个项目的详细信息，加入到项目列表中，key为'projectDetailList'
			for (Map<String, Object> project : projects) {
				String prjname = (String) project.get("prjname");
				List<Map<String, Object>> projectDetailList = operationDAO.selectProjectDetail(stockcode, prjname);
				project.put("projectDetailList", projectDetailList);
			}
			projectsrc.put("projectList", projects);
		}
		return projectsrcs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OperationService#queryOperation(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryOperation\")")
	public Map<String, Object> queryOperation(String stockcode) {
		Map<String, Object> operations = operationDAO.selectOperation(stockcode);
		return operations;
	}

}

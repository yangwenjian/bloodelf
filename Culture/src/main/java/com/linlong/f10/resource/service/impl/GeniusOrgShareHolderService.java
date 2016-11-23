/**   
 * @Title: GeniusOrgShareHolderService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月21日 上午8:53:09
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.dto.resource.OrgPosition;
import com.linlong.f10.resource.geniusdao.OrgShareHolderDAO;
import com.linlong.f10.resource.service.OrgShareHolderService;

/**
 * @ClassName: GeniusOrgShareHolderService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月21日 上午8:53:09
 * 
 */
@Service
public class GeniusOrgShareHolderService implements OrgShareHolderService {

	@Autowired
	private OrgShareHolderDAO orgShareHolderDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OrgShareHolderService#queryOrgPosition
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryOrgPosition\")")
	public Map<String, Map<String, Object>> queryOrgPosition(String stockcode) {
		// List<OrgPosition> orgPositions = new ArrayList<OrgPosition>();
		Map<String, Map<String, Object>> orgPositions = new HashMap<String, Map<String, Object>>();
		List<Date> enddates = orgShareHolderDAO.selectNodeDate(stockcode);
		for (Date enddate : enddates) {
			// OrgPosition orgPosition = new OrgPosition();
			// orgPosition.setEnddate(enddate);
			List<Map<String, Object>> resultMaps = orgShareHolderDAO.selectOrgPosition(stockcode, enddate);
			Map<String, Object> orgPosition = new HashMap<String, Object>();
			for (Map<String, Object> resultMap : resultMaps) {
				orgPosition.put((String) resultMap.get("holdType"), (BigDecimal) resultMap.get("holdNum") + "\n"
						+ (BigDecimal) resultMap.get("holdPct"));
			}
			orgPositions.put(new SimpleDateFormat("yyyy-MM-dd").format(enddate), orgPosition);
		}
		return orgPositions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OrgShareHolderService#queryFunPosition
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryFundPosition\")")
	public List<Map<String, Object>> queryFundPosition(String stockcode) {
		List<Map<String, Object>> fundPostions = orgShareHolderDAO.selectFundPosition(stockcode);
		return fundPostions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OrgShareHolderService#queryOrgHolders
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryOrgHolders\")")
	public List<OrgPosition> queryOrgHolders(String stockcode) {
		List<OrgPosition> orgPositions = new ArrayList<OrgPosition>();
		List<Date> enddates = orgShareHolderDAO.selectOrgHolderDate(stockcode);
		for (Date enddate : enddates) {
			OrgPosition orgPosition = new OrgPosition();
			orgPosition.setEnddate(enddate);
			List<Map<String, Object>> resultMaps = orgShareHolderDAO.selectOrgHoler(stockcode, enddate);
			orgPosition.setPositions(resultMaps);
			orgPositions.add(orgPosition);
		}
		return orgPositions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OrgShareHolderService#queryFundPositionBrief
	 * (java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> queryFundPositionBrief(String stockcode) {
		List<Map<String, Object>> fundPostions = orgShareHolderDAO.selectFundPositionBrief(stockcode);
		return fundPostions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.OrgShareHolderService#queryOrgHoldersBrief
	 * (java.lang.String)
	 */
	@Override
	public OrgPosition queryOrgHoldersBrief(String stockcode) {
		List<Date> enddates = orgShareHolderDAO.selectOrgHolderDate(stockcode);
		OrgPosition orgPosition = new OrgPosition();
		if (CollectionUtils.isNotEmpty(enddates)) {
			Date enddate = enddates.get(0);
			orgPosition.setEnddate(enddate);
			List<Map<String, Object>> resultMaps = orgShareHolderDAO.selectOrgHolerBrief(stockcode, enddate);
			orgPosition.setPositions(resultMaps);
		}
		return orgPosition;
	}

}

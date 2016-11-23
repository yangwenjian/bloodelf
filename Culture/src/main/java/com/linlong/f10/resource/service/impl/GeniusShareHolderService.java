/**   
 * @Title: GeniusShareHolderService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月21日 上午1:31:07
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.dto.resource.ShareHolderInfo;
import com.linlong.f10.resource.geniusdao.ShareHolderDAO;
import com.linlong.f10.resource.service.ShareHolderService;

/**
 * @ClassName: GeniusShareHolderService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月21日 上午1:31:07
 * 
 */
@Service
public class GeniusShareHolderService implements ShareHolderService {

	@Autowired
	private ShareHolderDAO shareHolderDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ShareHolderService#queryHoldSitu(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryHoldSitu\")")
	public List<Map<String, Object>> queryHoldSitu(String stockcode) {
		List<Map<String, Object>> resultMaps = shareHolderDAO
				.selectHoldSitu(stockcode);
		return resultMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ShareHolderService#queryShareHolders
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value="shareHolderInfoCache", key="#stockcode.concat(\"queryShareHolders\")")
	public ShareHolderInfo queryShareHolders(String stockcode) {
		ShareHolderInfo shareHolder = new ShareHolderInfo();
		Map<String, Object> totHolders = shareHolderDAO
				.selectTotHolders(stockcode);
		if (totHolders != null) {
			shareHolder.setChangeDate((Date) totHolders.get("changedate"));
			shareHolder.setTotalHolders((BigDecimal) totHolders
					.get("totHolder"));
		}
		String totalShare = shareHolderDAO.selectTotalShare(stockcode);
		shareHolder.setTotalShare(totalShare);
		List<Map<String, Object>> newHolders = shareHolderDAO
				.selectNewHolders(stockcode);
		shareHolder.setNewHolders(newHolders);
		List<Map<String, Object>> floatHolders = shareHolderDAO
				.selectFloatHolders(stockcode);
		shareHolder.setFloatHolders(floatHolders);
//		List<Map<String, Object>> limitHolders = shareHolderDAO
//				.selectLimitHolders(stockcode);
//		shareHolder.setLimitHolders(limitHolders);
		return shareHolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ShareHolderService#queryHisShareHolders
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryHisShareHolders\")")
	public List<ShareHolderInfo> queryHisShareHolders(String stockcode) {
		List<ShareHolderInfo> shareHolders = new ArrayList<ShareHolderInfo>();
		List<Date> changedates = shareHolderDAO.selectHisDate(stockcode);
		for (Date date : changedates) {
			ShareHolderInfo shareHolder = new ShareHolderInfo();
			Map<String, Object> totHolders = shareHolderDAO
					.selectHisTotHolders(stockcode, date);
			if (totHolders != null) {
				shareHolder.setChangeDate((Date) totHolders.get("changedate"));
				shareHolder.setTotalHolders((BigDecimal) totHolders
						.get("totHolder"));
			}
			String totalShare = shareHolderDAO.selectHisTotalShare(stockcode,
					date);
			shareHolder.setTotalShare(totalShare);
			List<Map<String, Object>> newHolders = shareHolderDAO
					.selectHisNewHolders(stockcode, date);
			shareHolder.setNewHolders(newHolders);
			List<Map<String, Object>> floatHolders = shareHolderDAO
					.selectHisFloatHolders(stockcode, date);
			shareHolder.setFloatHolders(floatHolders);
//			List<Map<String, Object>> limitHolders = shareHolderDAO
//					.selectHisLimitHolders(stockcode, date);
//			shareHolder.setLimitHolders(limitHolders);
			shareHolders.add(shareHolder);
		}
		return shareHolders;
	}

}

/**   
 * @Title: GeniusStockRequireService.java
 * @Package com.linlong.f10.resource.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月14日 上午2:35:52
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

import com.linlong.f10.dto.resource.ShareChange;
import com.linlong.f10.dto.resource.ShareInfo;
import com.linlong.f10.dto.resource.StockIndicator;
import com.linlong.f10.resource.geniusdao.StockRequireDAO;
import com.linlong.f10.resource.service.StockRequireService;

/**
 * @ClassName: GeniusStockRequireService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午2:35:52
 * 
 */
@Service
public class GeniusStockRequireService implements StockRequireService {

	@Autowired
	private StockRequireDAO stockReqDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#querySpeSug(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"querySpeSug\")")
	public List<String> querySpeSug(String stockcode) {
		List<String> list = new ArrayList<String>();
		List<Map<String, Object>> resultList = stockReqDAO
				.selectSpeSug(stockcode);
		for (Map<String, Object> map : resultList) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			list.add(format.format((Date) map.get("HINT_DT"))
					+ (String) map.get("ESP_HINT"));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#queryPerforPre(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryPerformance\")")
	public List<String> queryPerformance(String stockcode) {
		List<String> list = new ArrayList<String>();
		List<Map<String, Object>> resultList = stockReqDAO
				.selectPerformance(stockcode);
		for (Map<String, Object> map : resultList) {
			list.add((String) map.get("FORECAST"));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#queryIndicator(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="stringCache", key="#stockcode.concat(\"queryIndiTitle\")")
	public String queryIndiTitle(String stockcode) {
		List<Map<String, Object>> resultList = stockReqDAO
				.selectIndiTitle(stockcode);
		if (CollectionUtils.isNotEmpty(resultList)) {
			return (String) resultList.get(0).get("FORECAST");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#queryReport(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryIndicator\")")
	public List<StockIndicator> queryIndicator(String stockcode) {
		List<StockIndicator> list = new ArrayList<StockIndicator>();
		Integer isSeaRep = stockReqDAO.selectIsSeasonReport(stockcode);
		if (new Integer(0).equals(isSeaRep)) {
			List<Map<String, Object>> noneSeaReps = stockReqDAO
					.selectNoneSeasonReport(stockcode);
			for (Map<String, Object> noneSeaRep : noneSeaReps) {
				StockIndicator indicator = new StockIndicator();
				indicator.setEndDate((Date) noneSeaRep.get("ENDDATE"));
				indicator.setEpsned((BigDecimal) noneSeaRep.get("EPSNED"));
				indicator.setBpsned((BigDecimal) noneSeaRep.get("BPSNED"));
				indicator.setPscr((BigDecimal) noneSeaRep.get("PS_CR"));
				indicator.setPsup((BigDecimal) noneSeaRep.get("PS_UP"));
				indicator
						.setPsNetVal((BigDecimal) noneSeaRep.get("PS_NET_VAL"));
				indicator.setRoed((BigDecimal) noneSeaRep.get("ROED"));
				indicator.setIncj((BigDecimal) noneSeaRep.get("INC_J"));
				list.add(indicator);
			}
		} else {
			List<Map<String, Object>> seaReps = stockReqDAO
					.selectSeasonReport(stockcode);
			for (Map<String, Object> seaRep : seaReps) {
				StockIndicator indicator = new StockIndicator();
				indicator.setEndDate((Date) seaRep.get("ENDDATE"));
				indicator.setEpsned((BigDecimal) seaRep.get("EPSNED"));
				indicator.setBpsned((BigDecimal) seaRep.get("BPSNED"));
				indicator.setPscr((BigDecimal) seaRep.get("PS_CR"));
				indicator.setPsup((BigDecimal) seaRep.get("PS_UP"));
				indicator.setPsNetVal((BigDecimal) seaRep.get("PS_NET_VAL"));
				indicator.setRoed((BigDecimal) seaRep.get("ROED"));
				indicator.setIncj((BigDecimal) seaRep.get("INC_J"));
				list.add(indicator);
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#queryShare(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(value="shareInfoCache", key="#stockcode.concat(\"queryShare\")")
	public ShareInfo queryShare(String stockcode) {
		Map<String, Object> result = stockReqDAO.selectShare(stockcode);
		ShareInfo shareInfo = new ShareInfo();
		shareInfo.setChangeDate((Date) result.get("CHANGEDATE"));
		shareInfo.setTotal((BigDecimal) result.get("TOTAL"));
		shareInfo.setFlShare((BigDecimal) result.get("FL_SHR"));
		shareInfo.setFlAShare((BigDecimal) result.get("FL_ASHR"));
		shareInfo.setbShare((BigDecimal) result.get("B_SHR"));
		shareInfo.sethShare((BigDecimal) result.get("H_SHR"));
		shareInfo.setTotLtdfl((BigDecimal) result.get("TOT_LTDFL"));
		return shareInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#queryReview(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryReview\")")
	public List<String> queryReview(String stockcode) {
		List<Map<String, Object>> reviews = stockReqDAO.selectReview(stockcode);
		List<String> list = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map : reviews) {
			list.add(format.format(map.get("DECLAREDATE")) + ": "
					+ map.get("REVIEW_CONTENT") + " " + map.get("SOURCE_NAME"));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#queryAllotment(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryAllotment\")")
	public List<String> queryAllotment(String stockcode) {
		List<Map<String, Object>> dividends = stockReqDAO
				.selectDividend(stockcode);
		List<Map<String, Object>> allotments = stockReqDAO
				.selectAllotment(stockcode);
		List<String> list = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map : dividends) {
			String dividend = "分红：【" + map.get("PRG") + "】 "
					+ map.get("DIV_INFO");
			if (map.get("PRG").equals("实施")) {
				dividend += "(股权登记日:" + format.format(map.get("DIR_DCL_DATE"))
						+ ")";
			} else {
				dividend += "(公告日期:" + format.format(map.get("DIR_DCL_DATE"))
						+ ")";
			}
			list.add(dividend);
		}
		for (Map<String, Object> map : allotments) {
			String dividend = "配股：】" + map.get("PRG") + "】 "
					+ map.get("ALLOT_INFO");
			if (map.get("PRG").equals("实施")) {
				dividend += "(股权登记日:" + format.format(map.get("declaredate"))
						+ ")";
			} else {
				dividend += "(公告日期:" + format.format(map.get("declaredate"))
						+ ")";
			}
			list.add(dividend);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#queryShareChange
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryShareChange\")")
	public List<ShareChange> queryShareChange(String stockcode) {
		List<Map<String, Object>> shareChangeMaps = stockReqDAO
				.selectShareChange(stockcode);
		List<ShareChange> list = new ArrayList<ShareChange>();
		for (Map<String, Object> shareChangeMap : shareChangeMaps) {
			ShareChange shareChange = new ShareChange();
			shareChange.setName((String) shareChangeMap.get("MNG_NAME"));
			shareChange.setRelation((String) shareChangeMap.get("RELATION"));
			shareChange.setStockcode((String) shareChangeMap.get("STK_CODE"));
			shareChange.setChangeVol((BigDecimal) shareChangeMap
					.get("CHNG_VOL"));
			shareChange.setEndVol((BigDecimal) shareChangeMap.get("END_VOL"));
			shareChange.setChegEp((BigDecimal) shareChangeMap.get("CHEG_EP"));
			shareChange
					.setChangeReason((String) shareChangeMap.get("CHNG_RSN"));
			shareChange.setChangeDate((Date) shareChangeMap.get("CHNG_DATE"));
			list.add(shareChange);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.StockRequireService#Announcement(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryAnnouncement\")")
	public List<Map<String, String>> queryAnnouncement(String stockcode) {
		List<Map<String, Object>> announceResults = stockReqDAO
				.selectAnnouncement(stockcode);
		List<Map<String, String>> announcements = new ArrayList<Map<String, String>>();
		for (Map<String, Object> announceResult : announceResults) {
			Map<String, String> announcement = new HashMap<String, String>();
			announcement.put("title", (String) announceResult.get("TITLE"));
			announcement.put("content",
					(String) announceResult.get("TXT_CONTENT"));
			announcements.add(announcement);
		}
		return announcements;
	}

}

/**   
 * @Title: GeniusDividendService.java
 * @Package com.linlong.f10.resource.service.impl
 * @author  yangwenjian   
 * @date 2016年7月19日 上午10:53:15
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.DividendDAO;
import com.linlong.f10.resource.service.DividendService;

/**
 * @ClassName: GeniusDividendService
 * @Description:
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月19日 上午10:53:15
 * 
 */
@Service
public class GeniusDividendService implements DividendService {
	@Autowired
	private DividendDAO dividendDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.DividendService#queryShareCons(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"Devidend.qeuryShareCons\")")
	public List<Map<String, Object>> queryShareCons(String stockcode) {
		List<Map<String, Object>> shareCons = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> resultMaps = dividendDAO
				.selectShareCons(stockcode);
		for (Map<String, Object> map : resultMaps) {
			Map<String, Object> shareCon = new HashMap<String, Object>();
			shareCon.put("changedate", map.get("CHANGEDATE"));
			shareCon.put("total", map.get("TOTAL"));
			shareCon.put("flshr", map.get("FL_SHR"));
			shareCon.put("flashr", map.get("FL_ASHR"));
			shareCon.put("listFlashr", map.get("LIST_FL_ASHR"));
			shareCon.put("mngfl", map.get("MNG_FL"));
			shareCon.put("strapla", map.get("SRTA_PLA"));
			shareCon.put("fundpla", map.get("FUND_PLA"));
			shareCon.put("legpla", map.get("LEG_PLA"));
			shareCon.put("bshr", map.get("B_SHR"));
			shareCon.put("hshr", map.get("H_SHR"));
			shareCon.put("sshr", map.get("S_SHR"));
			shareCon.put("nshr", map.get("N_SHR"));
			shareCon.put("othAbroad", map.get("OTH_ABROAD"));
			shareCon.put("othfl", map.get("OTH_FL"));
			shareCon.put("totNonfl", map.get("TOT_NONFL"));
			shareCon.put("totLtdfl", map.get("TOT_LTDFL"));
			shareCon.put("stateLegshr", map.get("STATE_LEG_SHR"));
			shareCon.put("state", map.get("STATE"));
			shareCon.put("stateleg", map.get("STATE_LEG"));
			shareCon.put("domleg", map.get("DOM_LEG"));
			shareCon.put("domNatural", map.get("DOM_NATURAL"));
			shareCon.put("othspon", map.get("OTH_SPON"));
			shareCon.put("frgnshr", map.get("FRGN_SHR"));
			shareCon.put("frgnleg", map.get("FRGN_LEG"));
			shareCon.put("frgnNatural", map.get("FRGN_NATURAL"));
			shareCon.put("raisleg", map.get("RAIS_LEG"));
			shareCon.put("innerEmpl", map.get("INNER_EMPL"));
			shareCon.put("mngNonfl", map.get("MNG_NONFL"));
			shareCon.put("bref", map.get("BREF"));
			shareCon.put("trans", map.get("TRANS"));
			shareCon.put("othNonfl", map.get("OTH_NONFL"));
			shareCons.add(shareCon);
		}
		return shareCons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.DividendService#queryShareChange(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"DividendServicequeryShareChange\")")
	public List<Map<String, Object>> queryShareChange(String stockcode) {
		List<Map<String, Object>> resultMaps = dividendDAO
				.selectShareChange(stockcode);
		return resultMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.DividendService#queryDividend(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"DividendServicequeryDividend\")")
	public List<Map<String, Object>> queryDividend(String stockcode) {
		List<Map<String, Object>> resultMaps = dividendDAO
				.selectDividend(stockcode);
		return resultMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.DividendService#queryAllotment(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"DividendService.queryAllotment\")")
	public List<Map<String, Object>> queryAllotment(String stockcode) {
		List<Map<String, Object>> resultMaps = dividendDAO
				.selectAllotment(stockcode);
		return resultMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.DividendService#queryAddition(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"DividendServicequeryAddition\")")
	public List<Map<String, Object>> queryAddition(String stockcode) {
		List<Map<String, Object>> resultMaps = dividendDAO
				.selectAddition(stockcode);
		return resultMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.DividendService#queryConvBond(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryConvBond\")")
	public List<Map<String, Object>> queryConvBond(String stockcode) {
		List<Map<String, Object>> resultMaps = dividendDAO
				.selectConvBond(stockcode);
		return resultMaps;
	}
}

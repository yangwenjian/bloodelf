/**   
 * @Title: GeniusComProfileService.java
 * @Package com.linlong.f10.resource.serviceImpl
 * @author  yangwenjian   
 * @date 2016年7月18日 上午9:17:11
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.ComProfileDAO;
import com.linlong.f10.resource.service.ComProfileService;

/**
 * @ClassName: GeniusComProfileService
 * @Description: 公司概况服务类
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月18日 上午9:17:11
 * 
 */
@Service
public class GeniusComProfileService implements ComProfileService {

	@Autowired
	private ComProfileDAO comProfileDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryShareHolder(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryShareHolder\")")
	public Map<String, String> queryShareHolder(String stockcode) {
		Map<String, String> shareHolder = new HashMap<String, String>();
		Map<String, Object> resultMap = comProfileDAO.selectShareHolder(stockcode);
		shareHolder.put("enddate", (String) new SimpleDateFormat("yyyy-MM-dd").format((Date) resultMap.get("ENDDATE")));
		shareHolder.put("ctrlName", (String) resultMap.get("CTRL_NAME"));
		shareHolder.put("ctrlNameInfo", (String) resultMap.get("CTRL_NAME_INFO"));
		shareHolder.put("relation", (String) resultMap.get("RELATION"));
		return shareHolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryInvestKeyPoint
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "stringCache", key = "#stockcode.concat(\"queryInvestKeyPoint\")")
	public String queryInvestKeyPoint(String stockcode) {
		String investKeyPoint = comProfileDAO.selectInvestKeyPoint(stockcode);
		return investKeyPoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryPubOffer(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryPubOffer\")")
	public Map<String, Object> queryPubOffer(String stockcode) {
		Map<String, Object> pubOffer = new HashMap<String, Object>();
		Map<String, Object> resultMap = comProfileDAO.selectPubOffer(stockcode);
		pubOffer.put("apldate", resultMap.get("ONL_APL_DATE"));
		pubOffer.put("listdate", new SimpleDateFormat("yyyy-MM-dd").format(resultMap.get("LIST_DATE")));
		pubOffer.put("totalShare", resultMap.get("TTL_SHR"));
		pubOffer.put("netcap", resultMap.get("NET_CAP"));
		pubOffer.put("issPrice", resultMap.get("ISS_PRC"));
		pubOffer.put("pe", resultMap.get("PE"));
		pubOffer.put("eps", resultMap.get("EPS"));
		pubOffer.put("naps", resultMap.get("NAPS"));
		pubOffer.put("sharePercent", resultMap.get("ISS_SHR_PCT"));
		pubOffer.put("lotRate", resultMap.get("ONL_LOT_RATE"));
		pubOffer.put("topenFirst", resultMap.get("TOPEN_FIR"));
		pubOffer.put("tcloseFirst", resultMap.get("TCLOSE_FIR"));
		pubOffer.put("mainundwrt", resultMap.get("MAIN_UNDWRT"));
		pubOffer.put("issCls", resultMap.get("ISS_CLS"));
		pubOffer.put("orgNames", resultMap.get("ORG_NAMES"));
		return pubOffer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryComProfile(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryComProfile\")")
	public Map<String, Object> queryComProfile(String stockcode) {
		Map<String, Object> profile = new HashMap<String, Object>();
		Map<String, Object> infoResultMap = comProfileDAO.selectComInfo(stockcode);
		if (infoResultMap != null) {
			profile.put("stockname", infoResultMap.get("STOCKSNAME"));
			profile.put("stockcode", infoResultMap.get("STOCKCODE"));
			profile.put("cname", infoResultMap.get("Cname"));
			profile.put("ename", infoResultMap.get("Ename"));
			profile.put("regiAddr", infoResultMap.get("REGI_ADDR"));
			profile.put("officeAddr", infoResultMap.get("OFFICE_ADDR"));
			profile.put("briefBuz", infoResultMap.get("PRI_BIZ"));
			profile.put("website", infoResultMap.get("WEB_SITE"));
			profile.put("email", infoResultMap.get("EMAIL"));
			profile.put("induName", infoResultMap.get("INDU_NAME"));
			profile.put("builddate", infoResultMap.get("BUILD_DATE"));
			profile.put("regicap", infoResultMap.get("REGI_CAP"));
			profile.put("legPerson", infoResultMap.get("LEG_PERSON"));
			profile.put("manager", infoResultMap.get("GEN_MANAGER"));
			profile.put("boardSectry", infoResultMap.get("BOARD_SECTRY"));
			profile.put("redr", infoResultMap.get("REPR"));
			profile.put("tel", infoResultMap.get("TEL"));
			profile.put("fax", infoResultMap.get("FAX"));
			profile.put("postcode", infoResultMap.get("POSTCODE"));
			profile.put("lawOrgCode", infoResultMap.get("LAW_ORGCODE"));
			profile.put("accOrgCode", infoResultMap.get("ACC_ORGCODE"));
			profile.put("briefIntro", infoResultMap.get("BRIEF_INTRO"));
		}
		List<String> indexResultList = comProfileDAO.selectIndex(stockcode);
		profile.put("index", indexResultList);

		List<Map<String, Object>> formerResultMaps = comProfileDAO.selectFormerName(stockcode);
		List<String> formerNames = new ArrayList<String>();
		for (Map<String, Object> formerResultMap : formerResultMaps) {
			formerNames.add(formerResultMap.get("stocksname") + "("
					+ new SimpleDateFormat("yyyy-MM-dd").format(formerResultMap.get("CHANGEDATE")) + "停用)");
		}
		profile.put("formerNames", formerNames);

		List<Map<String, Object>> relStResultMap = comProfileDAO.selectRelStock(stockcode);
		profile.put("relStocks", relStResultMap);
		return profile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryComProfile(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryComProfileBrief\")")
	public Map<String, Object> queryComProfileBrief(String stockcode) {
		Map<String, Object> profile = new HashMap<String, Object>();
		Map<String, Object> infoResultMap = comProfileDAO.selectComInfo(stockcode);
		if (infoResultMap != null) {
			profile.put("stockname", infoResultMap.get("STOCKSNAME"));
			profile.put("stockcode", infoResultMap.get("STOCKCODE"));
			profile.put("cname", infoResultMap.get("Cname"));
			profile.put("ename", infoResultMap.get("Ename"));
			profile.put("legPerson", infoResultMap.get("LEG_PERSON"));
			profile.put("website", infoResultMap.get("WEB_SITE"));
			profile.put("email", infoResultMap.get("EMAIL"));
			profile.put("regicap", infoResultMap.get("REGI_CAP"));
			profile.put("tel", infoResultMap.get("TEL"));
		}
		return profile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryEmplSitu(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryEmplSitu\")")
	public List<Map<String, Object>> queryEmplSitu(String stockcode) {
		List<Map<String, Object>> emplSitus = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> resultMaps = comProfileDAO.selectEmplSitu(stockcode);
		for (Map<String, Object> resultMap : resultMaps) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("enddate", resultMap.get("ENDDATE"));
			map.put("hrcls", resultMap.get("HR_CLS"));
			map.put("itemName", resultMap.get("ITEM_NAME"));
			map.put("emplNum", resultMap.get("EPLE_NUM"));
			map.put("emplPct", resultMap.get("EPLE_PCT"));
			emplSitus.add(map);
		}
		return emplSitus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryFirstShareHolder
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryFirstShareHolder\")")
	public List<Map<String, Object>> queryFirstShareHolder(String stockcode) {
		List<Map<String, Object>> emplSitus = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> resultMaps = comProfileDAO.selectFirstShareHolder(stockcode);
		for (Map<String, Object> resultMap : resultMaps) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("declaredate", resultMap.get("DECLAREDATE"));
			map.put("holderName", resultMap.get("HOLDER_NAME"));
			map.put("holderType", resultMap.get("SHR_HLD_TYPE"));
			map.put("isControl", resultMap.get("IS_CTRL"));
			map.put("holdNum", resultMap.get("HLD_NUM"));
			map.put("sharePct", resultMap.get("AFT_SHR_PCT"));
			map.put("startdate", resultMap.get("START_DATE"));
			map.put("lockPeriod", resultMap.get("LOCK_PERIOD"));
			emplSitus.add(map);
		}
		return emplSitus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryStockName(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "stringCache", key = "#stockcode.concat(\"queryStockName\")")
	public String queryStockName(String stockcode) {
		return comProfileDAO.selectStockName(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ComProfileService#queryEmplInfo(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "#stockcode.concat(\"queryEmplInfo\")")
	public Map<String, Object> queryEmplInfo(String stockcode) {
		return comProfileDAO.selectEmplInfo(stockcode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linlong.f10.resource.service.ComProfileService#queryStockCodes()
	 */
	@Override
	public List<String> queryStockCodes() {
		return comProfileDAO.selectStockCodes();
	}
}

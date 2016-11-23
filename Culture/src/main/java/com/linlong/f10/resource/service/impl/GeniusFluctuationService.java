/**   
 * @Title: GeniusFluctuationService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:30:31
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.dto.resource.Fluctuation;
import com.linlong.f10.resource.geniusdao.FluctuationDAO;
import com.linlong.f10.resource.service.FluctuationService;

/**
 * @ClassName: GeniusFluctuationService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:30:31
 * 
 */
@Service
public class GeniusFluctuationService implements FluctuationService {

	@Autowired
	private FluctuationDAO fluctuationDAO;

	/*
	 * (non-Javadoc)
	 * 不加缓存
	 * @see
	 * com.linlong.f10.resource.service.FluctuationService#queryFluctuation(
	 * java.lang.String)
	 */
	@Override
	public List<Fluctuation> queryFluctuation(String stockcode) {
		List<Fluctuation> list = new ArrayList<Fluctuation>();
		List<Map<String, Object>> resultMaps = fluctuationDAO
				.selectEnddates(stockcode);
		for (Map<String, Object> resultMap : resultMaps) {
			Fluctuation fluct = new Fluctuation();
			Date enddate = (Date) resultMap.get("enddate");
			fluct.setEnddate(enddate);
			Map<String, Object> flucInfo = fluctuationDAO.selectFlucInfo(
					stockcode, enddate);
			fluct.setRefName((String) flucInfo.get("refName"));
			fluct.setFflChangePct((BigDecimal) flucInfo.get("fflChangePct"));
			fluct.setVolume((BigDecimal) flucInfo.get("volume"));
			fluct.setValue((BigDecimal) flucInfo.get("value"));
			List<Map<String, Object>> details = fluctuationDAO
					.selectFlucDetail(stockcode, enddate);
			fluct.setDetails(details);
			list.add(fluct);
		}
		return list;
	}

}

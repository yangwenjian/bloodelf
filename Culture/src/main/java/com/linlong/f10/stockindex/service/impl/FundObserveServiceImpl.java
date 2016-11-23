/**   
* @Title: FundObserverServiceImpl.java
* @Package com.linlong.f10.stockindex.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月4日 下午4:19:32
* @version V1.0   
*/
package com.linlong.f10.stockindex.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.FundObserveDAO;
import com.linlong.f10.stockindex.service.FundObserveService;

/**
 * @ClassName: FundObserverServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月4日 下午4:19:32
 * 
 */
@Service
public class FundObserveServiceImpl implements FundObserveService{

	@Autowired
	private FundObserveDAO fundObserveDAO;
	/* (non-Javadoc)
	 * @see com.linlong.f10.stockindex.service.FundObserveService#getFundMarketDynamicTitle()
	 */
	@Override
	public List<Map<String, Object>> getFundMarketDynamicTitle() {
		return fundObserveDAO.getFundMarketDynamicTitle();
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.stockindex.service.FundObserveService#getFundMarketDynamicInfo()
	 */
	@Override
	public List<Map<String, Object>> getFundMarketDynamicInfo() {
		List<Map<String, Object>> titles = this.getFundDynamicTitle();
		if (CollectionUtils.isEmpty(titles)) {
			return new ArrayList<Map<String, Object>>();
		}
		List<String> guidList = new ArrayList<String>();
		for(Map<String, Object> title : titles){
			guidList.add((String) title.get("guid"));
		}
		return fundObserveDAO.getFundMarketDynamicInfo(guidList);
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.stockindex.service.FundObserveService#getFundResearchTitle()
	 */
	@Override
	public List<Map<String, Object>> getFundResearchTitle() {
		return fundObserveDAO.getFundResearchTitle();
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.stockindex.service.FundObserveService#getFundResearchInfo()
	 */
	@Override
	public List<Map<String, Object>> getFundResearchInfo() {
		List<Map<String, Object>> titles = this.getFundResearchTitle();
		if (CollectionUtils.isEmpty(titles)) {
			return new ArrayList<Map<String, Object>>();
		}
		List<String> guidList = new ArrayList<String>();
		for(Map<String, Object> title : titles){
			guidList.add((String) title.get("guid"));
		}
		return fundObserveDAO.getFundResearchInfo(guidList);
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.stockindex.service.FundObserveService#getFundDynamicTitle()
	 */
	@Override
	public List<Map<String, Object>> getFundDynamicTitle() {
		return fundObserveDAO.getFundDynamicTitle();
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.stockindex.service.FundObserveService#getFundDynamic()
	 */
	@Override
	public List<Map<String, Object>> getFundDynamicInfo() {
		List<Map<String, Object>> titles = this.getFundDynamicTitle();
		if (CollectionUtils.isEmpty(titles)) {
			return new ArrayList<Map<String, Object>>();
		}
		List<String> guidList = new ArrayList<String>();
		for(Map<String, Object> title : titles){
			guidList.add((String) title.get("guid"));
		}
		return fundObserveDAO.getFundDynamicInfo(guidList);
	}

}

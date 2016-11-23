/**   
 * @Title: ThirdBoadServiceImpl.java
 * @Package com.linlong.f10.stockindex.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年8月4日 上午1:17:55
 * @version V1.0   
 */
package com.linlong.f10.stockindex.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.ThirdBoardDAO;
import com.linlong.f10.stockindex.service.ThirdBoardService;

/**
 * @ClassName: ThirdBoadServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月4日 上午1:17:55
 * 
 */
@Service
public class ThirdBoardServiceImpl implements ThirdBoardService {

	@Autowired
	private ThirdBoardDAO thirdBoardDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.stockindex.service.ThirdBoardService#getMarketStastistics
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "publicCache", key = "\"getMarketStastistics\"")
	public Map<String, Object> getMarketStastistics() {

		return thirdBoardDAO.getMarketStastistics("三板市场行情%");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.stockindex.service.ThirdBoardService#getReviewTitle(java
	 * .lang.String)
	 */
	@Override
	public List<Map<String, Object>> getReviewTitle() {
		return thirdBoardDAO.getReviewTitle("三板市场行情%");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.stockindex.service.ThirdBoardService#getReviewContent
	 * (java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getReviewContent() {
		List<Map<String, Object>> reviewTitles = this.getReviewTitle();
		if (CollectionUtils.isEmpty(reviewTitles)) {
			return new ArrayList<Map<String, Object>>();
		}
		List<String> guidList = new ArrayList<String>();
		for (Map<String, Object> reviewTitle : reviewTitles) {
			guidList.add((String) reviewTitle.get("guid"));
		}
		return thirdBoardDAO.getReviewContent(guidList);
	}

}

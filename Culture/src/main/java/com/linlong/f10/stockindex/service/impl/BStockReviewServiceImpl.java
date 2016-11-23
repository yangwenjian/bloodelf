/**   
 * @Title: BStockReviewServiceImpl.java
 * @Package com.linlong.f10.stockindex.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年8月5日 上午2:35:53
 * @version V1.0   
 */
package com.linlong.f10.stockindex.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.BStockReviewDAO;
import com.linlong.f10.stockindex.service.BStockReviewService;

/**
 * @ClassName: BStockReviewServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月5日 上午2:35:53
 * 
 */
@Service
public class BStockReviewServiceImpl implements BStockReviewService {

	@Autowired
	private BStockReviewDAO bstockReviewDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.stockindex.service.BStockReviewService#getTapeReviewTitle
	 * ()
	 */
	@Override
	public List<Map<String, Object>> getTapeReviewTitle() {
		return bstockReviewDAO.getTapeReviewTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.stockindex.service.BStockReviewService#getTapeReviewInfoc
	 * ()
	 */
	@Override
	public List<Map<String, Object>> getTapeReviewInfoc() {
		List<Map<String, Object>> reviewTitles = this.getTapeReviewTitle();
		if (CollectionUtils.isEmpty(reviewTitles)) {
			return new ArrayList<Map<String, Object>>();
		}
		List<String> guidList = new ArrayList<String>();
		for(Map<String, Object> reviewTitle : reviewTitles){
			guidList.add((String)reviewTitle.get("guid"));
		}
		return bstockReviewDAO.getTapeReviewInfoc(guidList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.stockindex.service.BStockReviewService#getIndividualReview
	 * ()
	 */
	@Override
	public List<Map<String, Object>> getIndividualReview() {
		return bstockReviewDAO.getIndividualReview();
	}

}

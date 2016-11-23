/**   
 * @Title: GeniusReviewService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:28:31
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.ReviewDAO;
import com.linlong.f10.resource.service.ReviewService;

/**
 * @ClassName: GeniusReviewService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:28:31
 * 
 */
@Service
public class GeniusReviewService implements ReviewService {
	@Autowired
	private ReviewDAO reviewDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ReviewService#queryReviews(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryReviews\")")
	public List<Map<String, Object>> queryReviews(String stockcode) {
		List<Map<String,Object>> reviews = reviewDAO.selectReviews(stockcode);
		return reviews;
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.resource.service.ReviewService#queryReviewTitles(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> queryReviewTitles(String stockcode) {
		List<Map<String, Object>> reviewTitles = reviewDAO.selectReviewTitles(stockcode);
		return reviewTitles;
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.resource.service.ReviewService#queryReviewById(java.lang.String)
	 */
	@Override
	public Map<String, Object> queryReviewById(String guid) {
		Map<String,Object> review = reviewDAO.selectReviewById(guid);
		return review;
	}

}

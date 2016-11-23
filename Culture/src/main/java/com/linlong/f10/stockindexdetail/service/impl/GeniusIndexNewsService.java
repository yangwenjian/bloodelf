/**   
 * @Title: GeniusIndexNewsService.java
 * @Package com.linlong.f10.stockindexdetail.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年8月1日 下午2:38:32
 * @version V1.0   
 */
package com.linlong.f10.stockindexdetail.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindexdetail.geniusdao.IndexNewsDAO;
import com.linlong.f10.stockindexdetail.service.IndexNewsService;

/**
 * @ClassName: GeniusIndexNewsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月1日 下午2:38:32
 * 
 */
@Service
public class GeniusIndexNewsService implements IndexNewsService {

	@Autowired
	private IndexNewsDAO indexNewsDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.stockindexdetail.service.IndexNewsService#queryIndexNews
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryIndexNews\")")
	public List<Map<String, Object>> queryIndexNews(String stockcode) {
		List<Map<String, Object>> indexNews = indexNewsDAO
				.selectIndexNews(stockcode);
		return indexNews;
	}

}

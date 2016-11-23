/**   
 * @Title: GeniusShareReformService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:32:31
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.ShareReformDAO;
import com.linlong.f10.resource.service.ShareReformService;

/**
 * @ClassName: GeniusShareReformService
 * @Description: 
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:32:31
 * 
 */
@Service
public class GeniusShareReformService implements ShareReformService {

	@Autowired
	private ShareReformDAO shareReformDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ShareReformService#queryShareChange
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value="publicCache", key="#stockcode.concat(\"ShareReformService.queryShareChange\")")
	public Map<String, Object> queryShareChange(String stockcode) {
		Map<String, Object> shareChange = shareReformDAO
				.selectShareChange(stockcode);
		return shareChange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ShareReformService#queryRestrictShare
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryRestrictShare\")")
	public List<Map<String, Object>> queryRestrictShare(String stockcode) {
		List<Map<String, Object>> restrictShares = shareReformDAO
				.selectRestrictShare(stockcode);
		return restrictShares;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ShareReformService#queryRelatedInfo
	 * (java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> queryRelatedInfo(String stockcode) {
		List<Map<String, Object>> relatedInfo = shareReformDAO
				.selectRelatedInfo(stockcode);
		return relatedInfo;
	}

}

/**   
 * @Title: GeniusExecutiveService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:16:56
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.ExecutiveDAO;
import com.linlong.f10.resource.service.ExecutiveService;

/**
 * @ClassName: GeniusExecutiveService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:16:56
 * 
 */
@Service
public class GeniusExecutiveService implements ExecutiveService {
	@Autowired
	private ExecutiveDAO executiveDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ExecutiveService#queryExecChange(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryExecChange\")")
	public List<Map<String, Object>> queryExecChange(String stockcode) {
		List<Map<String, Object>> execChanges = executiveDAO
				.selectExecChange(stockcode);
		return execChanges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ExecutiveService#queryExecDetail(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryExecDetail\")")
	public List<Map<String, Object>> queryExecDetail(String stockcode) {
		List<Map<String, Object>> resultMaps = executiveDAO
				.selectExecDetail(stockcode);
		return resultMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ExecutiveService#queryExecChReason(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryExecChReason\")")
	public List<Map<String, Object>> queryExecChReason(String stockcode) {
		List<Map<String, Object>> resultMaps = executiveDAO
				.selectExecChReason(stockcode);
		return resultMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ExecutiveService#queryExecPT(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryExecPT\")")
	public List<Map<String, Object>> queryExecPT(String stockcode) {
		List<Map<String, Object>> resultMaps = executiveDAO
				.selectExecPT(stockcode);
		return resultMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ExecutiveService#queryStIncetive(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryStIncetive\")")
	public List<Map<String, Object>> queryStIncetive(String stockcode) {
		List<Map<String, Object>> resultMap = executiveDAO
				.selectStIncetive(stockcode);
		return resultMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ExecutiveService#queryExecIntro(java
	 * .lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryExecIntro\")")
	public List<Map<String, Object>> queryExecIntro(String stockcode) {
		List<Map<String, Object>> resultMaps = executiveDAO
				.selectExecIntro(stockcode);
		return resultMaps;
	}

}

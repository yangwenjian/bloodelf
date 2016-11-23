/**   
* @Title: GeniusIndexSampleService.java
* @Package com.linlong.f10.stockindexdetail.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月1日 下午2:40:15
* @version V1.0   
*/
package com.linlong.f10.stockindexdetail.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindexdetail.geniusdao.IndexSampleDAO;
import com.linlong.f10.stockindexdetail.service.IndexSampleService;

/**
 * @ClassName: GeniusIndexSampleService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月1日 下午2:40:15
 * 
 */
@Service
public class GeniusIndexSampleService implements IndexSampleService{

	@Autowired
	private IndexSampleDAO indexSampleDAO;
	/* (non-Javadoc)
	 * @see com.linlong.f10.stockindexdetail.service.IndexSampleService#queryIndexSample(java.lang.String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryIndexSample\")")
	public List<Map<String, Object>> queryIndexSample(String stockcode) {
		List<Map<String, Object>> indexSamples = indexSampleDAO.selectIndexSample(stockcode);
		return indexSamples;
	}

}

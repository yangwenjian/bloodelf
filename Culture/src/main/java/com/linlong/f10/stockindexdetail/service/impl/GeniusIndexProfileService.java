/**   
* @Title: GeniusIndexProfileService.java
* @Package com.linlong.f10.stockindexdetail.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月1日 下午2:40:01
* @version V1.0   
*/
package com.linlong.f10.stockindexdetail.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindexdetail.geniusdao.IndexProfileDAo;
import com.linlong.f10.stockindexdetail.service.IndexProfileService;

/**
 * @ClassName: GeniusIndexProfileService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月1日 下午2:40:01
 * 
 */
@Service
public class GeniusIndexProfileService implements IndexProfileService{

	@Autowired
	private IndexProfileDAo indexProfileDAO;
	/* (non-Javadoc)
	 * @see com.linlong.f10.stockindexdetail.service.IndexProfileService#queryIndexProfile(java.lang.String)
	 */
	@Override
	@Cacheable(value="publicCache", key="#stockcode.concat(\"queryIndexProfile\")")
	public Map<String, Object> queryIndexProfile(String stockcode) {
		Map<String, Object> indexProfile = indexProfileDAO.selectIndexProfile(stockcode);
		return indexProfile;
	}

}

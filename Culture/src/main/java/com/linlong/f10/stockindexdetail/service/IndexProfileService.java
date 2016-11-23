/**   
* @Title: IndexProfileService.java
* @Package com.linlong.f10.stockindexdetail.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月1日 下午2:38:51
* @version V1.0   
*/
package com.linlong.f10.stockindexdetail.service;

import java.util.Map;

/**
 * @ClassName: IndexProfileService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月1日 下午2:38:51
 * 
 */
public interface IndexProfileService {
	public Map<String, Object> queryIndexProfile(String indexcode);
}

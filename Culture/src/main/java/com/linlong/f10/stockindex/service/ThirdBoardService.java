/**   
* @Title: ThirdBoadService.java
* @Package com.linlong.f10.stockindex.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月4日 上午1:16:46
* @version V1.0   
*/
package com.linlong.f10.stockindex.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ThirdBoadService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月4日 上午1:16:46
 * 
 */
public interface ThirdBoardService {
	public Map<String, Object> getMarketStastistics();

	public List<Map<String, Object>> getReviewTitle();

	public List<Map<String, Object>> getReviewContent();
}

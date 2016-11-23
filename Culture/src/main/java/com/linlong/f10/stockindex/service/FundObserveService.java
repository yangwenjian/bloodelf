/**   
* @Title: FundObserveService.java
* @Package com.linlong.f10.stockindex.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月4日 下午4:18:53
* @version V1.0   
*/
package com.linlong.f10.stockindex.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FundObserveService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月4日 下午4:18:53
 * 
 */
public interface FundObserveService {
	public List<Map<String, Object>> getFundMarketDynamicTitle();

	public List<Map<String, Object>> getFundMarketDynamicInfo();

	public List<Map<String, Object>> getFundResearchTitle();

	public List<Map<String, Object>> getFundResearchInfo();

	public List<Map<String, Object>> getFundDynamicTitle();

	public List<Map<String, Object>> getFundDynamicInfo();
}

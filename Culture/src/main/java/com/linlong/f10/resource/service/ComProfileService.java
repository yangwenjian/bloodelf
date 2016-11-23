/**   
 * @Title: ComProfileService.java
 * @Package com.linlong.f10.resource.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月18日 上午9:02:38
 * @version V1.0   
 */
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ComProfileService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月18日 上午9:02:38
 * 
 */
public interface ComProfileService {
	
	public String queryStockName(String stockcode);
	
	public List<String> queryStockCodes();
	
	public Map<String, String> queryShareHolder(String stockcode);

	public String queryInvestKeyPoint(String stockcode);

	public Map<String, Object> queryPubOffer(String stockcode);

	public Map<String, Object> queryComProfile(String stockcode);
	
	public List<Map<String, Object>> queryEmplSitu(String stockcode);
	
	public Map<String, Object> queryEmplInfo(String stockcode);
	
	public List<Map<String, Object>> queryFirstShareHolder(String stockcode);

	/**
	* @Title: queryComProfileBrief
	* @Description: 查看公司概况简要信息
	* @param 
	* @return Map<String,Object>    返回类型
	*/
	Map<String, Object> queryComProfileBrief(String stockcode);
}

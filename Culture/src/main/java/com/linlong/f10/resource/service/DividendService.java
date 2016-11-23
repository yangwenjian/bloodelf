/**   
* @Title: DividendService.java
* @Package com.linlong.f10.resource.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月19日 上午10:50:22
* @version V1.0   
*/
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DividendService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月19日 上午10:50:22
 * 
 */
public interface DividendService {
	public List<Map<String, Object>> queryShareCons(String stockcode);
	public List<Map<String, Object>> queryShareChange(String stockcode);
	public List<Map<String, Object>> queryDividend(String stockcode);
	public List<Map<String, Object>> queryAllotment(String stockcode);
	public List<Map<String, Object>> queryAddition(String stockcode);
	public List<Map<String, Object>> queryConvBond(String stockcode);
}

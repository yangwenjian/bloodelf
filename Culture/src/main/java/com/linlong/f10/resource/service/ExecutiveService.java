/**   
* @Title: ExecutiveService.java
* @Package com.linlong.f10.resource.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月22日 上午8:16:33
* @version V1.0   
*/
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExecutiveService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:16:33
 * 
 */
public interface ExecutiveService {
	public List<Map<String, Object>> queryExecChange(String stockcode);
	public List<Map<String, Object>> queryExecDetail(String stockcode);
	public List<Map<String, Object>> queryExecChReason(String stockcode);
	public List<Map<String, Object>> queryExecPT(String stockcode);
	public List<Map<String, Object>> queryStIncetive(String stockcode);
	public List<Map<String, Object>> queryExecIntro(String stockcode);
}

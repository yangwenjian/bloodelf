/**   
 * @Title: OperationService.java
 * @Package com.linlong.f10.resource.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:26:19
 * @version V1.0   
 */
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

import com.linlong.f10.dto.resource.Operation;

/**
 * @ClassName: OperationService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:26:19
 * 
 */
public interface OperationService {
	public Map<String, Object> queryBusiness(String stockcode);

	public List<Operation> queryMainBusiness(String stockcode);

	public List<Operation> querySecInfo(String stockcode);

	public List<Map<String, Object>> queryRaiseInfo(String stockcode);

	public List<Map<String, Object>> queryProject(String stockcode);

	public Map<String, Object> queryOperation(String stockcode);

	public Operation queryMainBusinessBrief(String stockcode);
}

/**   
* @Title: FinancialService.java
* @Package com.linlong.f10.resource.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月22日 上午8:20:43
* @version V1.0   
*/
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FinancialService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:20:43
 * 
 */
public interface FinancialService {
	public List<Map<String, Object>> queryEnddate(String stockcode);
	public List<Map<String, Object>> queryInvestment(String stockcode);
	public List<Map<String, Object>> queryDebtPaying(String stockcode);
	public List<Map<String, Object>> queryDeveloping(String stockcode);
	public List<Map<String, Object>> queryCapStruct(String stockcode);
	public List<Map<String, Object>> queryOpeEfficiency(String stockcode);
	public List<Map<String, Object>> queryProfit(String stockcode);
	public List<Map<String, Object>> queryCashFlow(String stockcode);
	public List<Map<String, Object>> queryRisk(String stockcode);
	public List<Map<String, Object>> queryAudit(String stockcode);
	public List<Map<String, Object>> queryCapDebtTable(String stockcode);
	public List<Map<String, Object>> queryProfitTable(String stockcode);
	public List<Map<String, Object>> queryCashFlowTable(String stockcode);
}

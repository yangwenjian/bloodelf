/**   
* @Title: FinancialDAO.java
* @Package com.linlong.f10.resource.geniusdao
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月22日 上午8:20:27
* @version V1.0   
*/
package com.linlong.f10.resource.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FinancialDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:20:27
 * 
 */
public interface FinancialDAO {
	public List<Map<String, Object>> selectEnddate(String stockcode);
	public List<Map<String, Object>> selectDebtPaying(String stockcode);
	public List<Map<String, Object>> selectDeveloping(String stockcode);
	public List<Map<String, Object>> selectCapStruct(String stockcode);
	public List<Map<String, Object>> selectOpeEfficiency(String stockcode);
	public List<Map<String, Object>> selectProfite(String stockcode);
	public List<Map<String, Object>> selectCashFlow(String stockcode);
	public List<Map<String, Object>> selectRisk(String stockcode);
	public List<Map<String, Object>> selectAudit(String stockcode);
	public List<Map<String, Object>> selectCapDebtTable(String stockcode);
	public List<Map<String, Object>> selectProfitTable(String stockcode);
	public List<Map<String, Object>> selectCashFlowTable(String stockcode);
}

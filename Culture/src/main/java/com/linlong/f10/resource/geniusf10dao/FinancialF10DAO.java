/**   
* @Title: FinancialDAO.java
* @Package com.linlong.f10.resource.geniusdao
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月22日 上午8:20:27
* @version V1.0   
*/
package com.linlong.f10.resource.geniusf10dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FinancialDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:20:27
 * 
 */
public interface FinancialF10DAO {
	public List<Map<String, Object>> selectInvestment(String stockcode);
}

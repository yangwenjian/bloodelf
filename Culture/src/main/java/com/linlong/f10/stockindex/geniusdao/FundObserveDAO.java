/**   
 * @Title: FundObserveDAO.java
 * @Package com.linlong.f10.stockindex.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年8月4日 下午4:05:08
 * @version V1.0   
 */
package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FundObserveDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月4日 下午4:05:08
 * 
 */
public interface FundObserveDAO {
	public List<Map<String, Object>> getFundMarketDynamicTitle();

	public List<Map<String, Object>> getFundMarketDynamicInfo(List<String> guidList);

	public List<Map<String, Object>> getFundResearchTitle();

	public List<Map<String, Object>> getFundResearchInfo(List<String> guidList);

	public List<Map<String, Object>> getFundDynamicTitle();

	public List<Map<String, Object>> getFundDynamicInfo(List<String> guidList);
}

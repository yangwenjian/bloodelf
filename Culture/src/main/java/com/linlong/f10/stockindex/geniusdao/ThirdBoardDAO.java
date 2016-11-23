/**   
 * @Title: ThirdBoardDAO.java
 * @Package com.linlong.f10.stockindex.geniusdao
 * @Description: 查询新三板行情信息
 * @author  yangwenjian   
 * @date 2016年8月4日 上午1:08:55
 * @version V1.0   
 */
package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ThirdBoardDAO
 * @Description: 查询新三板行情信息
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月4日 上午1:08:55
 * 
 */
public interface ThirdBoardDAO {
	public Map<String, Object> getMarketStastistics(String keyword);

	public List<Map<String, Object>> getReviewTitle(String keyword);

	public List<Map<String, Object>> getReviewContent(List<String> guidList);
}

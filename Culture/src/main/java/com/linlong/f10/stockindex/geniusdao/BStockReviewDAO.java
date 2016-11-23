/**   
 * @Title: BStockReview.java
 * @Package com.linlong.f10.stockindex.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年8月5日 上午2:09:36
 * @version V1.0   
 */
package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BStockReview
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月5日 上午2:09:36
 * 
 */
public interface BStockReviewDAO {
	public List<Map<String, Object>> getTapeReviewTitle();

	public List<Map<String, Object>> getTapeReviewInfoc(List<String> guidList);

	public List<Map<String, Object>> getIndividualReview();
}

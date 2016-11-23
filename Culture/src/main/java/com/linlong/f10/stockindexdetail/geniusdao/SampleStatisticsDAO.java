/**   
* @Title: SampleStatisticsDAO.java
* @Package com.linlong.f10.stockindexdetail.geniusdao
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月1日 下午2:27:15
* @version V1.0   
*/
package com.linlong.f10.stockindexdetail.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SampleStatisticsDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月1日 下午2:27:15
 * 
 */
public interface SampleStatisticsDAO {
	public List<Map<String, Object>> selectSampleStatistics(String indexcode);
}

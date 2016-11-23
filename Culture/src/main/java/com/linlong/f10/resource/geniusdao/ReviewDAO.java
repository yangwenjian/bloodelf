/**   
 * @Title: ReviewDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:27:48
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReviewDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:27:48
 * 
 */
public interface ReviewDAO {
	public List<Map<String, Object>> selectReviews(String stockcode);

	public List<Map<String, Object>> selectReviewTitles(String stockcode);

	public Map<String, Object> selectReviewById(String guid);

	public List<Map<String, Object>> selectReviews2(String stockcode);
}

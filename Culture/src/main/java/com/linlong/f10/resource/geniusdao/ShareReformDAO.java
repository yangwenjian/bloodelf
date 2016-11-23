/**   
 * @Title: ShareReformDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:32:10
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShareReformDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:32:10
 * 
 */
public interface ShareReformDAO {
	public Map<String, Object> selectShareChange(String stockcode);

	public List<Map<String, Object>> selectRestrictShare(String stockcode);

	public List<Map<String, Object>> selectRelatedInfo(String stockcode);
}

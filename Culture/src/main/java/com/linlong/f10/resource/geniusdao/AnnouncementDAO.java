/**   
 * @Title: AnouncementDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:09:48
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AnouncementDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:09:48
 * 
 */
public interface AnnouncementDAO {

	public List<Map<String, Object>> selectAnnouncements(String stockcode);

	/**
	 * @Title: selectAnnouncementTitles
	 * @param
	 * @return List<Map<String,Object>> 返回类型
	 */
	public List<Map<String, Object>> selectAnnouncementTitles(String stockcode);

	/**
	 * @Title: selectAnnouncementById
	 * @param
	 * @return Map<String,Object> 返回类型
	 */
	public Map<String, Object> selectAnnouncementById(String discId);
}

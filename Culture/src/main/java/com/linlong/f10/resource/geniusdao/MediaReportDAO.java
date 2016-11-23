/**   
 * @Title: MediaReportDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:24:44
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MediaReportDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:24:44
 * 
 */
public interface MediaReportDAO {
	public List<Map<String, Object>> selectMediaReports(String stockcode);

	public List<Map<String, Object>> selectMediaReportTitles(String stockcode);

	public Map<String, Object> selectMediaReportById(String guid);

	public List<Map<String, Object>> selectMediaReports2(String stockcode);
}

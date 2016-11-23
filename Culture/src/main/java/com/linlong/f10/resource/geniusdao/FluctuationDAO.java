/**   
 * @Title: FluctuationDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:30:00
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: FluctuationDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:30:00
 * 
 */
public interface FluctuationDAO {
	public List<Map<String, Object>> selectEnddates(String stockcode);

	public Map<String, Object> selectFlucInfo(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public List<Map<String, Object>> selectFlucDetail(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);
}

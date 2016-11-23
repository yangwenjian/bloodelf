/**   
 * @Title: DividendDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月19日 上午11:01:29
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DividendDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月19日 上午11:01:29
 * 
 */
public interface DividendDAO {
	public List<Map<String, Object>> selectShareCons(String stockcode);
	public List<Map<String, Object>> selectShareChange(String stockcode);
	public List<Map<String, Object>> selectDividend(String stockcode);
	public List<Map<String, Object>> selectAllotment(String stockcode);
	public List<Map<String, Object>> selectAddition(String stockcode);
	public List<Map<String, Object>> selectConvBond(String stockcode);
}

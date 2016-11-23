/**   
 * @Title: OrgShareHolderService.java
 * @Package com.linlong.f10.resource.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月21日 上午8:52:42
 * @version V1.0   
 */
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

import com.linlong.f10.dto.resource.OrgPosition;

/**
 * @ClassName: OrgShareHolderService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月21日 上午8:52:42
 * 
 */
public interface OrgShareHolderService {
	public Map<String, Map<String, Object>> queryOrgPosition(String stockcode);

	public List<Map<String, Object>> queryFundPosition(String stockcode);

	public List<OrgPosition> queryOrgHolders(String stockcode);

	/**
	 * @Title: queryFundPositionBrief
	 * @Description: 基金持仓简略信息
	 * @param
	 * @return List<Map<String,Object>> 返回类型
	 */
	public List<Map<String, Object>> queryFundPositionBrief(String stockcode);

	/**
	 * @Title: queryOrgHoldersBrief
	 * @Description: 机构持股简略信息
	 * @param
	 * @return OrgPosition 返回类型
	 */
	public OrgPosition queryOrgHoldersBrief(String stockcode);
}

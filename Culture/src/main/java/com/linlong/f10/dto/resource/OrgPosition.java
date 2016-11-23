/**   
 * @Title: OrgPosition.java
 * @Package com.linlong.f10.dto.resource
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月21日 上午9:09:25
 * @version V1.0   
 */
package com.linlong.f10.dto.resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OrgPosition
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月21日 上午9:09:25
 * 
 */
public class OrgPosition {
	private Date enddate;
	private List<Map<String, Object>> positions;

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public List<Map<String, Object>> getPositions() {
		return positions;
	}

	public void setPositions(List<Map<String, Object>> positions) {
		this.positions = positions;
	}

}

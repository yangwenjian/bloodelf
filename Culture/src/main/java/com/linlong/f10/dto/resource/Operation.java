/**   
 * @Title: Operation.java
 * @Package com.linlong.f10.dto.resource
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月27日 上午5:39:49
 * @version V1.0   
 */
package com.linlong.f10.dto.resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Operation
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月27日 上午5:39:49
 * 
 */
public class Operation {
	private Date enddate;
	private List<Map<String, Object>> info;

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public List<Map<String, Object>> getInfo() {
		return info;
	}

	public void setInfo(List<Map<String, Object>> info) {
		this.info = info;
	}
}

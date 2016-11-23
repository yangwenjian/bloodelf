/**   
 * @Title: ShareHolderInfo.java
 * @Package com.linlong.f10.dto.resource
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月21日 上午5:44:28
 * @version V1.0   
 */
package com.linlong.f10.dto.resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShareHolderInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月21日 上午5:44:28
 * 
 */
public class ShareHolderInfo {
	private Date changeDate;
	private BigDecimal totalHolders;
	private List<Map<String, Object>> newHolders;
	private List<Map<String, Object>> floatHolders;
	private List<Map<String, Object>> limitHolders;
	private String totalShare;

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public BigDecimal getTotalHolders() {
		return totalHolders;
	}

	public void setTotalHolders(BigDecimal totalHolders) {
		this.totalHolders = totalHolders;
	}

	public List<Map<String, Object>> getNewHolders() {
		return newHolders;
	}

	public void setNewHolders(List<Map<String, Object>> newHolders) {
		this.newHolders = newHolders;
	}

	public List<Map<String, Object>> getFloatHolders() {
		return floatHolders;
	}

	public void setFloatHolders(List<Map<String, Object>> floatHolders) {
		this.floatHolders = floatHolders;
	}

	public List<Map<String, Object>> getLimitHolders() {
		return limitHolders;
	}

	public void setLimitHolders(List<Map<String, Object>> limitHolders) {
		this.limitHolders = limitHolders;
	}

	public String getTotalShare() {
		return totalShare;
	}

	public void setTotalShare(String totalShare) {
		this.totalShare = totalShare;
	}

}

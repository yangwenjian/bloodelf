/**   
* @Title: Fluctuation.java
* @Package com.linlong.f10.dto.resource
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月25日 下午4:22:57
* @version V1.0   
*/
package com.linlong.f10.dto.resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Fluctuation
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月25日 下午4:22:57
 * 
 */
public class Fluctuation {
	private Date enddate;
	private String refName;
	private BigDecimal fflChangePct;
	private BigDecimal volume;
	private BigDecimal value;
	private List<Map<String, Object>> details;
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public BigDecimal getFflChangePct() {
		return fflChangePct;
	}
	public void setFflChangePct(BigDecimal fflChangePct) {
		this.fflChangePct = fflChangePct;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public List<Map<String, Object>> getDetails() {
		return details;
	}
	public void setDetails(List<Map<String, Object>> details) {
		this.details = details;
	}
	
}

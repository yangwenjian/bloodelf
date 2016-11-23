/**   
* @Title: ShareInfo.java
* @Package com.linlong.f10.dto.stockrequire
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月14日 上午8:06:47
* @version V1.0   
*/
package com.linlong.f10.dto.resource;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ShareInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午8:06:47
 * 
 */
public class ShareInfo {
	private Date changeDate;
	private BigDecimal total;
	private BigDecimal flShare;
	private BigDecimal flAShare;
	private BigDecimal bShare;
	private BigDecimal hShare;
	private BigDecimal totLtdfl;
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getFlShare() {
		return flShare;
	}
	public void setFlShare(BigDecimal flShare) {
		this.flShare = flShare;
	}
	public BigDecimal getFlAShare() {
		return flAShare;
	}
	public void setFlAShare(BigDecimal flAShare) {
		this.flAShare = flAShare;
	}
	public BigDecimal getbShare() {
		return bShare;
	}
	public void setbShare(BigDecimal bShare) {
		this.bShare = bShare;
	}
	public BigDecimal gethShare() {
		return hShare;
	}
	public void sethShare(BigDecimal hShare) {
		this.hShare = hShare;
	}
	public BigDecimal getTotLtdfl() {
		return totLtdfl;
	}
	public void setTotLtdfl(BigDecimal totLtdfl) {
		this.totLtdfl = totLtdfl;
	}
	
}

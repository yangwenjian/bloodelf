/**   
 * @Title: ShareChange.java
 * @Package com.linlong.f10.dto.stockrequire
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月14日 上午10:50:37
 * @version V1.0   
 */
package com.linlong.f10.dto.resource;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ShareChange
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午10:50:37
 * 
 */
public class ShareChange {
	private String name;
	private String relation;
	private String stockcode;
	private BigDecimal changeVol;
	private BigDecimal endVol;
	private BigDecimal chegEp;
	private String changeReason;
	private Date changeDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public BigDecimal getChangeVol() {
		return changeVol;
	}

	public void setChangeVol(BigDecimal changeVol) {
		this.changeVol = changeVol;
	}

	public BigDecimal getEndVol() {
		return endVol;
	}

	public void setEndVol(BigDecimal endVol) {
		this.endVol = endVol;
	}

	public BigDecimal getChegEp() {
		return chegEp;
	}

	public void setChegEp(BigDecimal chegEp) {
		this.chegEp = chegEp;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

}

/**   
 * @Title: StockIndicator.java
 * @Package com.linlong.f10.dto.stockrequire
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月14日 上午7:31:21
 * @version V1.0   
 */
package com.linlong.f10.dto.resource;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: StockIndicator
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午7:31:21
 * 
 */
public class StockIndicator {
	private Date endDate;
	private BigDecimal epsned;
	private BigDecimal bpsned;
	private BigDecimal pscr;
	private BigDecimal psup;
	private BigDecimal psNetVal;
	private BigDecimal roed;
	private BigDecimal incj;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getEpsned() {
		return epsned;
	}

	public void setEpsned(BigDecimal epsned) {
		this.epsned = epsned;
	}

	public BigDecimal getPscr() {
		return pscr;
	}

	public void setPscr(BigDecimal pscr) {
		this.pscr = pscr;
	}

	public BigDecimal getPsup() {
		return psup;
	}

	public void setPsup(BigDecimal psup) {
		this.psup = psup;
	}

	public BigDecimal getPsNetVal() {
		return psNetVal;
	}

	public void setPsNetVal(BigDecimal psNetVal) {
		this.psNetVal = psNetVal;
	}

	public BigDecimal getRoed() {
		return roed;
	}

	public void setRoed(BigDecimal roed) {
		this.roed = roed;
	}

	public BigDecimal getIncj() {
		return incj;
	}

	public void setIncj(BigDecimal incj) {
		this.incj = incj;
	}

	public BigDecimal getBpsned() {
		return bpsned;
	}

	public void setBpsned(BigDecimal bpsned) {
		this.bpsned = bpsned;
	}

}

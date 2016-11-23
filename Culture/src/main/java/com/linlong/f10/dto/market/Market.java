package com.linlong.f10.dto.market;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName: Market
 * @Description: 股票行情DTO
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年6月23日 下午4:51:41
 *
 */
public class Market implements Serializable, Writeable {

	private static final long serialVersionUID = -5389122203423951741L;
	private String code;
	private BigDecimal floatShare;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getFloatShare() {
		return floatShare;
	}

	public void setFloatShare(BigDecimal floatShare) {
		this.floatShare = floatShare;
	}

	@Override
	public String toString() {
		return code + ":" + floatShare;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.f10.dto.Writeable#writeObject()
	 */
	@Override
	public String writeObject() {
		return this.toString();
	}

}

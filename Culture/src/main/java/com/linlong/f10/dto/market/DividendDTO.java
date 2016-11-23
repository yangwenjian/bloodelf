/**   
 * @Title: DividendBody.java
 * @Package com.linlong.f10.dto.market
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年10月13日 上午10:44:30
 * @version V1.0   
 */
package com.linlong.f10.dto.market;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DividendBody
 * @Description:
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年10月13日 上午10:44:30
 * 
 */
public class DividendDTO implements Serializable, Writeable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7430430650343109021L;
	private String stockcode;
	private String market;
	private List<Dividend> list = new ArrayList<Dividend>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linlong.f10.dto.market.Writeable#writeObject()
	 */
	@Override
	public String writeObject() {
		return this.toString();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(stockcode).append(",").append(market).append(":").append(list.size()).append("/");
		for (Dividend dividend : list) {
			buffer.append(dividend).append(";");
		}
		return buffer.toString();
	}

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public List<Dividend> getList() {
		return list;
	}

	public void setList(List<Dividend> list) {
		this.list = list;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

}

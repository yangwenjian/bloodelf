/**   
 * @Title: Dividend.java
 * @Package com.linlong.f10.dto.market
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年9月20日 上午8:18:07
 * @version V1.0   
 */
package com.linlong.f10.dto.market;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Dividend
 * @Description:除权实体
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月20日 上午8:18:07
 * 
 */
public class Dividend implements Serializable, Writeable {
	private static final long serialVersionUID = -1270146095456010462L;
	private String stockcode;
	private String market;
	private float bonus;
	private float cash;
	private float cap;
	private float allot;
	private float allotPrice;
	private Date exdividate;

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}

	public float getAllot() {
		return allot;
	}

	public void setAllot(float allot) {
		this.allot = allot;
	}

	public float getAllotPrice() {
		return allotPrice;
	}

	public void setAllotPrice(float allotPrice) {
		this.allotPrice = allotPrice;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(100);
		if (exdividate != null) {
			sb.append(exdividate.getTime()).append("%").append(String.format("%.4f", cash)).append(",")
					.append(String.format("%.4f", allot)).append(",").append(String.format("%.4f", allotPrice))
					.append(",").append(String.format("%.4f", bonus + cap));
		} else {
			System.out.println("wrong data!");
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linlong.f10.dto.market.Writeable#writeObject()
	 */
	@Override
	public String writeObject() {
		return this.toString();
	}

	public Date getExdividate() {
		return exdividate;
	}

	public void setExdividate(Date exdividate) {
		this.exdividate = exdividate;
	}

	public float getBonus() {
		return bonus;
	}

	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public float getCap() {
		return cap;
	}

	public void setCap(float cap) {
		this.cap = cap;
	}

	// public static void main(String[] args) {
	// Dividend d = new Dividend();
	// d.setEps(0.00002f);
	// d.setAllot(0.0323f);
	// d.setAllotPrice(0.23f);
	// System.out.println(d);
	// System.out.println(String.format("%.0f", d.getEps()));
	// System.out.println(new DecimalFormat("#.000000").format(d.getEps()));
	// NumberFormat ddf1 = NumberFormat.getNumberInstance();
	// ddf1.setMaximumFractionDigits(9);
	// System.out.println(ddf1.format(d.getEps()));
	// System.out.println(ddf1.format(d.getAllot()));
	// }
}

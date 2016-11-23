/**   
* @Title: IndustryCompany.java
* @Package com.linlong.f10.dto.resource
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月30日 上午2:30:54
* @version V1.0   
*/
package com.linlong.f10.dto.resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndustryCompany
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月30日 上午2:30:54
 * 
 */
public class IndustryCompany {
	private Date enddate;
	private List<Map<String, Object>> comList;
	private String companyName;
	private Double epspRank;
	private Double incjRank;
	private Double roedRank;
	private Double bpsnedRank;
	private Double psnetvalRank;
	private Double inciRank;
	private Double selrintRank;
	private Double baloRank;
	private Double caplabRank;
	private Double opetaiRank;
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Double getEpspRank() {
		return epspRank;
	}
	public void setEpspRank(Double epspRank) {
		this.epspRank = epspRank;
	}
	public Double getIncjRank() {
		return incjRank;
	}
	public void setIncjRank(Double incjRank) {
		this.incjRank = incjRank;
	}
	public Double getRoedRank() {
		return roedRank;
	}
	public void setRoedRank(Double roedRank) {
		this.roedRank = roedRank;
	}
	public Double getBpsnedRank() {
		return bpsnedRank;
	}
	public void setBpsnedRank(Double bpsnedRank) {
		this.bpsnedRank = bpsnedRank;
	}
	public Double getPsnetvalRank() {
		return psnetvalRank;
	}
	public void setPsnetvalRank(Double psnetvalRank) {
		this.psnetvalRank = psnetvalRank;
	}
	public Double getInciRank() {
		return inciRank;
	}
	public void setInciRank(Double inciRank) {
		this.inciRank = inciRank;
	}
	public Double getSelrintRank() {
		return selrintRank;
	}
	public void setSelrintRank(Double selrintRank) {
		this.selrintRank = selrintRank;
	}
	public Double getBaloRank() {
		return baloRank;
	}
	public void setBaloRank(Double baloRank) {
		this.baloRank = baloRank;
	}
	public Double getCaplabRank() {
		return caplabRank;
	}
	public void setCaplabRank(Double caplabRank) {
		this.caplabRank = caplabRank;
	}
	public Double getOpetaiRank() {
		return opetaiRank;
	}
	public void setOpetaiRank(Double opetaiRank) {
		this.opetaiRank = opetaiRank;
	}
	public List<Map<String, Object>> getComList() {
		return comList;
	}
	public void setComList(List<Map<String, Object>> comList) {
		this.comList = comList;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}

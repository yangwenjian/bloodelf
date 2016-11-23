package com.linlong.f10.dto.market;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryStockCode implements Serializable, Writeable {

	private static final long serialVersionUID = 2297780669820996632L;
	private String classifyCode;
	private List<String> codes = new ArrayList<String>();

	public String getClassifyCode() {
		return classifyCode;
	}

	public void setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
	}

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(200);
		buffer.append(classifyCode).append(":");
		for (int i = 0; i < codes.size() - 1; i++) {
			buffer.append(codes.get(i)).append(",");
		}
		buffer.append(codes.get(codes.size() - 1));
		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see com.f10.dto.Writeable#writeObject()
	 */
	@Override
	public String writeObject() {
		return this.toString();
	}
}

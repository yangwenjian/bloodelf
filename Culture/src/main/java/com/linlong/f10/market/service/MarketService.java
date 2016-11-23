package com.linlong.f10.market.service;

import java.util.List;

public interface MarketService {

	public void preReadData();
	
	public void writeDatatoFile();

	public String queryMarketFile(ContentType fileType);

	public String queryFloatShareFile(List<String> codes);
}

package com.linlong.f10.stockindex.geniusdao;

import java.util.Map;

public interface NewStockInfoDAO {

	Map<String, Object> getNewStockInfo(String title);
	
	Map<String, Object> getWaitListing();
	
	Map<String, Object> getLatestStock();
	
	Map<String, Object> getNewStockLocation();
	
}

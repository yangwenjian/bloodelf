package com.linlong.f10.stockindex.service;

import java.util.Map;

public interface NewStockInfoService {
	
	Map<String, Object> getTodayNewStockInfo();
	
	Map<String, Object> getWaitIssueStockInfo();

	Map<String, Object> getWaitListing();

	Map<String, Object> getLatestStock();

	Map<String, Object> getNewStockLocation();
	
}

package com.linlong.f10.stockindex.service;

import java.util.List;
import java.util.Map;

public interface NewsService {

	Map<String, Object> getTodayNews();
	
	Map<String, Object> getYestdayNews();
	
	List<Map<String, Object>> getTodayFocus();
	
}

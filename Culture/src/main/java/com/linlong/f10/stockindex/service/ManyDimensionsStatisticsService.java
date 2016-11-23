package com.linlong.f10.stockindex.service;

import java.util.List;
import java.util.Map;

public interface ManyDimensionsStatisticsService {

	List<Map<String, Object>> getDividendScheme();
	
	List<Map<String, Object>> getManyDimensionsStatistics();
	
}

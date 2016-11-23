package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

public interface ManyDimensionsStatisticsDAO {

	List<Map<String, Object>> getDividendScheme();
	
	List<Map<String, Object>> getManyDimensionsStatistics();
	
}

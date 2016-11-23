package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

public interface BruntTrendDAO {
	
	List<Map<String, Object>> getBruntTrend(String stockCode);
	
}

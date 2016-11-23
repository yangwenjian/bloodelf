package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

public interface NewsDAO {
	
	Map<String, Object> getTodayNews();
	
	Map<String, Object> getYestdayNews();
	
	List<Map<String, Object>> getTodayFocus();
	
}

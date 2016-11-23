package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

public interface InternationalNewsDAO {

	Map<String, Object> getGlobalIndex();
	
	List<String> getNewsGUID();
	
	List<Map<String, Object>> getInternationalNews(List<String> guids);
	
}

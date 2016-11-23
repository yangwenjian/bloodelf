package com.linlong.f10.stockindex.service;

import java.util.List;
import java.util.Map;

public interface InternationalNewsService {
	
	Map<String, Object> getGlobalIndex();
	
	List<Map<String, Object>> getInternationalNews();
	
}

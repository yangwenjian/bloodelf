package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

public interface IndustryStudyDAO {

	List<String> getNewsList();
	
	List<Map<String, Object>> getNews(List<String> guids);
	
}

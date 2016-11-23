package com.linlong.f10.stockindex.geniusdao;

import java.util.Map;

public interface TradeMemoDAO {

	Map<String, Object> getMemo(String indexCode);
	
}

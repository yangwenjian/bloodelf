package com.linlong.f10.market.geniusdao;

import java.util.List;
import java.util.Map;

import com.linlong.f10.dto.market.Dividend;

public interface WarrantDAO {

	List<Map<String, Object>> selectDevindend(String stockCode);

	List<Map<String, Object>> selectAdditional(String stockCode);
	
	List<Dividend> selectAllDividend();

}

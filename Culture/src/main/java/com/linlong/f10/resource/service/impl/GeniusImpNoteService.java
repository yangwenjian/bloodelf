/**   
 * @Title: GeniusImpNoteService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:22:27
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.ImpNoteDAO;
import com.linlong.f10.resource.service.ImpNoteService;

/**
 * @ClassName: GeniusImpNoteService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:22:27
 * 
 */
@Service
public class GeniusImpNoteService implements ImpNoteService {
	@Autowired
	private ImpNoteDAO impNoteDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.ImpNoteService#queryImpNotes(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value="listCache", key="#stockcode.concat(\"queryImpNotes\")")
	public List<Map<String, Object>> queryImpNotes(String stockcode) {
		List<Map<String, Object>> impNotes = impNoteDAO
				.selectImpNotes(stockcode);
		return impNotes;
	}

}

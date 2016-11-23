/**   
 * @Title: GeniusAnouncementService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:10:29
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.AnnouncementDAO;
import com.linlong.f10.resource.service.AnnouncementService;

/**
 * @ClassName: GeniusAnouncementService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:10:29
 * 
 */
@Service
public class GeniusAnnouncementService implements AnnouncementService {

	@Autowired
	private AnnouncementDAO announcementDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.AnnouncementService#queryAnnouncements
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryComAnnouncements\")")
	public List<Map<String, Object>> queryComAnnouncements(String stockcode) {
		List<Map<String, Object>> announcements = announcementDAO.selectAnnouncements(stockcode);
		return announcements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.AnnouncementService#queryComAnnounceTitle
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryComAnnounceTitle\")")
	public List<Map<String, Object>> queryComAnnounceTitle(String stockcode) {
		List<Map<String, Object>> announcementTitles = announcementDAO.selectAnnouncementTitles(stockcode);
		return announcementTitles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.AnnouncementService#QueryComAnnounceById
	 * (java.lang.String)
	 */
	@Override
	public Map<String, Object> QueryComAnnounceById(String discId) {
		Map<String, Object> announce = announcementDAO.selectAnnouncementById(discId);
		return announce;
	}

}

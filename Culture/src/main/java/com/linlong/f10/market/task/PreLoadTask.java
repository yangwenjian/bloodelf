package com.linlong.f10.market.task;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.market.service.FileService;
import com.linlong.f10.market.service.MarketService;

@Service("preLoadTask")
public class PreLoadTask {
	@Resource(name="etlService")
	private MarketService marketService;

	@Autowired
	private FileService persistentService;

	public void dailyTask() {
		persistentService.removeOldLockFile();
		marketService.preReadData();
	}
}

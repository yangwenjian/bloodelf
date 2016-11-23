/**   
 * @Title: HeartBeatTask.java
 * @Package com.linlong.f10.core.communication.task
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年10月9日 下午3:43:36
 * @version V1.0   
 */
package com.linlong.f10.core.communication.task;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.core.communication.CommunicationService;

/**
 * @ClassName: HeartBeatTask
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年10月9日 下午3:43:36
 * 
 */
@Service
public class HeartBeatTask {
	@Autowired
	private CommunicationService communicationService;

	private String heartBeat;
	public void checkHeartBeat() {
		//System.out.println("check heart beat......");
		if(StringUtils.isBlank(heartBeat)){
			System.out.println("重新启动Client");
			communicationService.stopClient();
			communicationService.startClient();
		}
		heartBeat = null;
	}

	public String getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(String heartBeat) {
		this.heartBeat = heartBeat;
	}
}

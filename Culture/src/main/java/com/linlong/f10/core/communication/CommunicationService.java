/**   
 * @Title: CommunicationService.java
 * @Package com.linlong.f10.core.communication
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年9月29日 下午1:30:51
 * @version V1.0   
 */
package com.linlong.f10.core.communication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.linlong.f10.core.authentication.service.TokenManager;
import com.linlong.f10.core.communication.task.HeartBeatTask;

/**
 * @ClassName: CommunicationService
 * @Description: 
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月29日 下午1:30:51
 * 
 */
@Service
public class CommunicationService {
	private MonitorClient monitorClient;
	private MonitorServer monitorServer;
	private Thread monitorClientThread;
	private Thread monitorServerThread;
	@Resource(name="redisTokenManager")
	private TokenManager tokenManager;
	@Autowired
	private HeartBeatTask heartBeatTask;
	@Value("${hostIP}")
	private String hostIP;
	@Value("${serverPort}")
	private int serverPort;
	@Value("${tcpServerPort}")
	private int tcpServerPort;

	@Value("${monitorPort}")
	private int monitorPort;
	@Value("${monitorServerPort}")
	private int monitorServerPort;
	@Value("${monitorServerIP}")
	private String monitorServerIP;

	@PostConstruct
	public void startServer() {
		monitorServer = new MonitorServer(tokenManager);
		monitorServer.setPort(tcpServerPort);
		monitorServerThread = new Thread(monitorServer);
		monitorServerThread.start();
	}

	@PostConstruct
	public void startClient() {
		monitorClient = new MonitorClient();
		monitorClient.setHeartBeatTask(heartBeatTask);
		monitorClient.setHostIP(hostIP);
		monitorClient.setPort(serverPort);
		monitorClient.setMonitorPort(monitorPort);
		monitorClient.setMonitorServerIP(monitorServerIP);
		monitorClient.setMonitorServerPort(monitorServerPort);
		monitorClientThread = new Thread(monitorClient);
		monitorClientThread.start();

	}

	public void stopServer() {
		if (monitorServerThread != null) {
			try {
				monitorServerThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopClient() {
		if (monitorClient != null) {
			monitorClient.setRunable(false);
		}
		if (monitorClientThread != null) {
			try {
				monitorClientThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

/**   
 * @Title: CommunicationServer.java
 * @Package com.linlong.f10.core.communication
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年9月29日 上午9:39:43
 * @version V1.0   
 */
package com.linlong.f10.core.communication;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.ExtensionRegistry;
import com.linlong.f10.core.communication.task.HeartBeatTask;
import com.linlong.f10.core.util.IPUtil;

/**
 * @ClassName: CommunicationServer
 * @Description:
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月29日 上午9:39:43
 * 
 */
public class MonitorClient implements Runnable {
	private String monitorServerIP = "192.168.4.138";
	private int monitorServerPort = 16833;
	private String hostIP = "192.168.4.56";
	private int port = 8080;
	private int monitorPort = 16833;
	private boolean runable = true;
	
	private  HeartBeatTask heartBeatTask;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		EventLoopGroup clientGroup = new NioEventLoopGroup();
		ChannelFuture future = null;

		// 注册SSAPotocol中的所有协议
		ExtensionRegistry registry = ExtensionRegistry.newInstance();
		SSAProtocol.registerAllExtensions(registry);

		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(clientGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel
									.pipeline()
									// .addLast(new LineBasedFrameDecoder(1024))
									// .addLast(new StringDecoder())
									.addLast(new ProtobufVarint32FrameDecoder())
									.addLast(new ProtobufVarint32LengthFieldPrepender())
									// .addLast("protobufDecoder", new
									// ProtobufDecoder(SSAProtocol.ServerStatus.getDefaultInstance(),
									// registry))
									.addLast(new ProtobufEncoder()).addLast(new SSAProtocolClientHandler(heartBeatTask));
						}
					});
			future = bootstrap.connect(new InetSocketAddress(monitorServerIP, monitorServerPort)).sync();
			future.addListener(new ChannelFutureListener() {
	            public void operationComplete(ChannelFuture f) throws Exception {
	                if (f.isSuccess()) {
	                    System.out.println("Started Tcp Client: " );
	                } else {
	                    System.out.println("Started Tcp Client Failed: ");
	                    f.channel().eventLoop().schedule(MonitorClient.this, 10, TimeUnit.SECONDS);
	                }
	            }
	        });
	    
			//int i = 1;
			while (runable) {
				synchronized (this) {
					//System.out.println("sendServerStatusTest 测试开始.");
					if (future != null) {
						//System.out.println("sendServerStatusTest 进入测试.");

						SSAProtocol.ServerStatus.Builder builder = SSAProtocol.ServerStatus.newBuilder();

						builder.setProtocolType(SSAProtocol.ProtocolType.SERVER_STATUS_PROTOCOL);

						builder.setServerPort(port);
						builder.setMonitorPort(monitorPort);
						builder.setServerIp(IPUtil.ipToLong(hostIP));
						builder.setServerNo(6);
						builder.setServerType(SSAProtocol.ServerStatus.ServerType.F10);
						builder.setAliveConnection(0);
						// 由于平台不同系统不同，使用反射来取值
						OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
						Method cpuLoadMethod = operatingSystemMXBean.getClass().getDeclaredMethod("getSystemCpuLoad");
						cpuLoadMethod.setAccessible(true);
						double cpuLoad = (double) cpuLoadMethod.invoke(operatingSystemMXBean);
						Method freeMemMethod = operatingSystemMXBean.getClass().getDeclaredMethod(
								"getFreePhysicalMemorySize");
						freeMemMethod.setAccessible(true);
						Long freeMem = (Long) freeMemMethod.invoke(operatingSystemMXBean);
						Method totalMemMethod = operatingSystemMXBean.getClass().getDeclaredMethod(
								"getTotalPhysicalMemorySize");
						totalMemMethod.setAccessible(true);
						Long totalMem = (Long) totalMemMethod.invoke(operatingSystemMXBean);
						double memLoad = (totalMem - freeMem) / (totalMem / 100);
						builder.setCpuLoad((int) cpuLoad);
						builder.setMemLoad((int)memLoad);
						builder.setStatus(1);

						builder.setMaxConnection(1500);
						builder.addBelongIsp(1);
						builder.addBelongIsp(3);

						builder.setBelongProvince(210000);
						builder.setBelongCity(210100);
						builder.setBelongCountry(210112);

						//System.out.println("ServerStatus协议资料:" + builder.build().toString());

						future.channel().write(builder.build());
						future.channel().flush();
						//System.out.println("sendServerStatusTest 发送第" + i + "资料.");
						// Thread.sleep(10);
					}
					//System.out.println("sendServerStatusTest 测试完成.");
					//i++;
					this.wait(5000);
				}
			}

			future.channel().close().sync();
		} catch (InterruptedException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			// 断线重连
			/*
			 * executor.execute(new Runnable(){ public void run(){ try{ try {
			 * TimeUnit.SECONDS.sleep(5); try { connect(); } catch (Exception e)
			 * { e.printStackTrace(); } } catch (InterruptedException e) {
			 * e.printStackTrace(); } }catch (InternalError e){
			 * e.printStackTrace(); } } });
			 */
			clientGroup.shutdownGracefully();
		}

	}

	public boolean isRunable() {
		return runable;
	}

	public void setRunable(boolean runable) {
		this.runable = runable;
	}

	public String getMonitorServerIP() {
		return monitorServerIP;
	}

	public void setMonitorServerIP(String monitorServerIP) {
		this.monitorServerIP = monitorServerIP;
	}

	public int getMonitorServerPort() {
		return monitorServerPort;
	}

	public void setMonitorServerPort(int monitorServerPort) {
		this.monitorServerPort = monitorServerPort;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

//	public static void main(String[] args) {
//		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
//		System.out.println(operatingSystemMXBean.getClass().getName());
//		for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
//			method.setAccessible(true);
//			if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers())) {
//				Object value;
//				try {
//					value = method.invoke(operatingSystemMXBean);
//				} catch (Exception e) {
//					value = e;
//				} // try
//				System.out.println(method.getName() + " = " + value);
//			} // if
//		} // for
//	}

	public int getMonitorPort() {
		return monitorPort;
	}

	public void setMonitorPort(int monitorPort) {
		this.monitorPort = monitorPort;
	}

	public HeartBeatTask getHeartBeatTask() {
		return heartBeatTask;
	}

	public void setHeartBeatTask(HeartBeatTask heartBeatTask) {
		this.heartBeatTask = heartBeatTask;
	}

}

/**   
 * @Title: CommunicationClient.java
 * @Package com.linlong.f10.core.communication
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年9月29日 上午9:39:31
 * @version V1.0   
 */
package com.linlong.f10.core.communication;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.linlong.f10.core.authentication.service.TokenManager;

/**
 * @ClassName: CommunicationClient
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月29日 上午9:39:31
 * 
 */
public class MonitorServer implements Runnable {
	private int port = 16833;
	private TokenManager tokenManager;
	public MonitorServer(TokenManager tokenManager){
		this.tokenManager = tokenManager;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public TokenManager getTokenManager() {
		return tokenManager;
	}
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		System.out.println("准备运行端口：" + port);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b = b.group(bossGroup, workerGroup);
			b = b.channel(NioServerSocketChannel.class);
			b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new SSAProtocolServerHandler(tokenManager));
				}
			});
			b = b.option(ChannelOption.SO_BACKLOG, 128);
			b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
		System.out.println("启动完成");
	}

}

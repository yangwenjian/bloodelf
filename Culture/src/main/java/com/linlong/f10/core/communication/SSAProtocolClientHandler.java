package com.linlong.f10.core.communication;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

import com.linlong.f10.core.communication.task.HeartBeatTask;

/**
 * Created by Administrator on 2016/4/15.
 */
public class SSAProtocolClientHandler extends ChannelInboundHandlerAdapter {

	
	private HeartBeatTask heartBeatTask;
	
	public SSAProtocolClientHandler(HeartBeatTask heartBeatTask) {
		this.heartBeatTask = heartBeatTask;
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf byteBuf = (ByteBuf) msg;
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		//System.out.println(Arrays.toString(bytes));
		heartBeatTask.setHeartBeat(Arrays.toString(bytes));		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("TCP通信异常");
		cause.printStackTrace();
		ctx.close();
	}

}

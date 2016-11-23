/**   
 * @Title: ResponseServerHandler.java
 * @Package com.linlong.f10.core.authentication.listener
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年9月26日 下午3:03:16
 * @version V1.0   
 */
package com.linlong.f10.core.communication;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.linlong.f10.core.authentication.service.TokenManager;
import com.linlong.f10.core.util.ByteConvert;

/**
 * @ClassName: ResponseServerHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月26日 下午3:03:16
 * 
 */
public class SSAProtocolServerHandler extends ChannelInboundHandlerAdapter {

	private TokenManager tokenManager;
	
	public SSAProtocolServerHandler(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//ctx.writeAndFlush("");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf byteBuf = (ByteBuf) msg;
		if(byteBuf.hasArray()){
			System.out.println("has array");
		}
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		//debug
		System.out.println("@bytes.length: " + bytes.length);
		for(int i = 0; i < bytes.length; i++){
			System.out.print(bytes[i] + ", ");
		}
		
		int size = bytes.length-12;
		byte[] bytes2 = new byte[size];
		for(int i = 0; i < bytes2.length; i++){
			bytes2[i] = bytes[bytes.length-1-i];
		}
		//debug
		System.out.println("@bytes2.length: " + bytes2.length);
		for(int i = 0; i < bytes2.length; i++){
			System.out.print(bytes2[i] + ", ");
		}
		
		//byteBuf.readBytes(bytes);
		//System.out.println(Arrays.toString(bytes));
		String tokenId = String.valueOf(ByteConvert.bytesToLong(bytes2));
		System.out.println("@tokenId: " + tokenId);
		tokenManager.removeToken(tokenId);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}

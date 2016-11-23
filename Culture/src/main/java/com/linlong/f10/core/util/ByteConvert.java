package com.linlong.f10.core.util;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2016/4/28.
 */
public class ByteConvert {

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE/Byte.SIZE);
        buffer.putLong(x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE/Byte.SIZE);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static void printHexString( byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() );
        }
    }
    
    public static void main(String[] args) {
    	byte[] bytes = longToBytes(2380603181617504336l);
		for(int i = 0; i < bytes.length; i++){
			System.out.print(bytes[i] + ", ");
		}
		
		ByteBuf byteBuf = Unpooled.buffer(bytes.length);
		byteBuf.writeBytes(bytes);
		
		byte[] recieve = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(recieve);
		for(int i = 0; i < recieve.length; i++){
			System.out.print(recieve[i] + ", ");
		}
	}
}

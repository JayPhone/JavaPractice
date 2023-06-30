package com.jayphone.practice.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;

import java.nio.charset.Charset;

/**
 * @author JayPhone
 * @description 4G模块发送器
 * @date 2023/6/9
 */
public class Module4GSender {
    /**
     * 发送短信
     *
     * @param phone
     * @param msg
     * @return
     */
    public static boolean sendMsg(String phone, String msg) {
        SocketChannel channel = ChannelMap.getFirst();
        if (channel == null) {
            return false;
        }

        String data = "86" + phone + ":0:" + msg;
//        byte[] data = send.getBytes(Charset.forName("GBK"));
//        ByteBuf buf = Unpooled.wrappedBuffer(data);
        channel.writeAndFlush(data);
        return true;

    }

    /**
     * 发送语音电话
     *
     * @param phone
     * @param msg
     * @return
     */
    public static boolean sendVoiceCall(String phone, String msg) {
        SocketChannel channel = ChannelMap.getFirst();
        if (channel == null) {
            return false;
        }

        String data = phone + ":3:" + msg;
//        byte[] data = send.getBytes(Charset.forName("GBK"));
//        ByteBuf buf = Unpooled.wrappedBuffer(data);
        channel.writeAndFlush(data);
        return true;
    }
}

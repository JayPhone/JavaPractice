package com.jayphone.practice.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.concurrent.TimeUnit;

/**
 * @author JayPhone
 * @description 4G模块发送器
 * @date 2023/6/9
 */
@Slf4j
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
        ChannelFuture channelFuture = channel.writeAndFlush(data);
        return true;

//        try {
//            return channelFuture.await(1, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return false;
//        }
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

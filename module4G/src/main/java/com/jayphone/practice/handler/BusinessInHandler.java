package com.jayphone.practice.handler;

import com.jayphone.practice.service.ChannelMap;
import com.jayphone.practice.service.Module4GServer;
import com.jayphone.practice.util.NettyUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import static com.jayphone.practice.constant.Module4GConstants.*;

/**
 * @author JayPhone
 * @description 接收客户端消息业务处理类
 * @date 2023/6/7
 */
@Slf4j
public class BusinessInHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 接入连接");
        ChannelMap.add(NettyUtil.getIP(ctx), (SocketChannel) ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 断开连接");
        ChannelMap.remove(NettyUtil.getIP(ctx));
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String data) {
        //接收注册包
        if (REGISTER.equals(data)) {
            log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 发送注册包");
        }

        //接收心跳包
        if (HEART_BEAT.equals(data)) {
            log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 发送心跳包");
        }

        //发送短信成功
        if (data.contains(MSG_SEND_SUCCESS)) {
            log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 短信发送成功");
        }

        //发送短信失败
        if (data.contains(MSG_SEND_FAILURE)) {
            log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 短信发送失败");
        }

        //拨打电话,未接通
        if (data.contains(TTS_CALL)) {
            log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 正在拨打对方电话，未接通状态");
        }

        //电话拨打成功，正在播放语音
        if (data.contains(TTS_CALL_PLAYING)) {
            log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 电话已接通，正在播放语音");
        }

        //电话未接通或挂断
        if (data.contains(TTS_CALL_FAILURE)) {
            log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 电话未接通或挂断");
        }

        //语音播放完毕，并已经挂断电话
        if (data.contains(TTS_CALL_SUCCESS)) {
            log.info("CLIENT" + NettyUtil.getRemoteAddress(ctx) + " 播放完毕，并已经挂断电话");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
//        log.info("消息读取完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error(cause.getMessage(), cause);
    }
}

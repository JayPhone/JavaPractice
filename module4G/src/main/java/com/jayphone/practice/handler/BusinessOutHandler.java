package com.jayphone.practice.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;
import java.nio.charset.Charset;

/**
 * @author JayPhone
 * @description 服务端发送处理类
 * @date 2023/6/9
 */
@Slf4j
public class BusinessOutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.connect(ctx, remoteAddress, localAddress, promise);
        log.info("服务已连接，{}，{}", remoteAddress, localAddress);
    }

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.bind(ctx, localAddress, promise);
        log.info("服务已绑定，{}", localAddress);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        String content = (String) msg;
        log.info("服务端发送消息：{}", content);
        byte[] data = content.getBytes(Charset.forName("GBK"));
        ByteBuf buf = Unpooled.wrappedBuffer(data);
        ctx.writeAndFlush(buf);
    }
}

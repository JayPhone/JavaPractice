package com.jayphone.practice.util;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author JayPhone
 * @description
 * @date 2023/6/9
 */
@Slf4j
public class NettyUtil {
    /**
     * 获取连接客户端地址
     *
     * @param ctx
     * @return
     */
    public static String getRemoteAddress(ChannelHandlerContext ctx) {
        String socketString = "";
        if (ctx.channel() != null) {
            socketString = ctx.channel().remoteAddress().toString();
        }
        return socketString;
    }

    /**
     * 获取连接的IP地址
     *
     * @param ctx
     * @return
     */
    public static String getIP(ChannelHandlerContext ctx) {
        String remoteAddress = getRemoteAddress(ctx);

        String ip = "";
        if (StringUtils.hasLength(remoteAddress)) {
            int colonAt = remoteAddress.indexOf(":");
            ip = remoteAddress.substring(1, colonAt);
        }
        return ip;
    }

    /**
     * 获取连接的端口
     *
     * @param ctx
     * @return
     */
    public static String getPort(ChannelHandlerContext ctx) {
        String remoteAddress = getRemoteAddress(ctx);

        String port = "";
        if (StringUtils.hasLength(remoteAddress)) {
            int colonAt = remoteAddress.indexOf(":");
            port = remoteAddress.substring(colonAt);
        }
        return port;
    }
}

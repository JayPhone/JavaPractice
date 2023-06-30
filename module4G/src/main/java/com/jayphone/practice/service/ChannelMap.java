package com.jayphone.practice.service;

import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JayPhone
 * @description
 * @date 2023/6/9
 */
@Slf4j
public class ChannelMap {
    private static final Map<String, SocketChannel> socketMap = new ConcurrentHashMap<>();

    /**
     * 添加socket
     *
     * @param clientId
     * @param socketChannel
     */
    public static void add(String clientId, SocketChannel socketChannel) {
        socketMap.put(clientId, socketChannel);
        log.info("客户端{}添加channel", clientId);
    }

    public static SocketChannel getFirst() {
        if (!CollectionUtils.isEmpty(socketMap)) {
            Map.Entry<String, SocketChannel> channelEntry = socketMap.entrySet().iterator().next();
            log.info("获取客户端{} channel", channelEntry.getKey());
            return channelEntry.getValue();
        } else {
            log.info("获取客户端channel失败，不存在任何客户端");
            return null;
        }
    }

    /**
     * 获取指定客户端channel
     *
     * @param clientId
     * @return
     */
    public static SocketChannel get(String clientId) {
        SocketChannel socketChannel = socketMap.get(clientId);
        if (socketChannel != null) {
            log.info("获取客户端{} channel", clientId);
            return socketChannel;
        } else {
            log.info("获取客户端{} channel，不存在", clientId);
            return null;
        }
    }

    /**
     * 移除socket
     *
     * @param clientId
     */
    public static void remove(String clientId) {
        SocketChannel remove = socketMap.remove(clientId);
        if (remove != null) {
            log.info("客户端{}移除channel成功", clientId);
        } else {
            log.info("客户端{}移除channel失败", clientId);
        }
    }
}

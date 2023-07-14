package com.jayphone.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/7
 */
@Slf4j
public class SocketThreadManager {
    private Map<String, SocketThread> socketThreadMap = new ConcurrentHashMap<>();

    public boolean hasSocketThread() {
        return !CollectionUtils.isEmpty(socketThreadMap);
    }

    public SocketThread getSocketThread() {
        if (CollectionUtils.isEmpty(socketThreadMap)) {
            log.info("获取设备失败，不存在任何设备");
            return null;
        }
        Map.Entry<String, SocketThread> entry = socketThreadMap.entrySet().iterator().next();
        log.info("获取设备{}socket", entry.getKey());
        return entry.getValue();
    }

    public void addSocketThread(String ip, SocketThread socketThread) {
        socketThreadMap.put(ip, socketThread);
        log.info("设备{}添加socket成功", ip);
    }

    public void removeSocketThread(String ip) {
        if (socketThreadMap.remove(ip) != null) {
            log.info("设备{}移除socket成功", ip);
        } else {
            log.info("设备{}移除socket失败", ip);
        }
    }

    public void closeSockets() {
        socketThreadMap.forEach((s, socketThread) -> socketThread.closeSocket());
        socketThreadMap.clear();
    }
}

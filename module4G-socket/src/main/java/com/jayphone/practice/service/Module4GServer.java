package com.jayphone.practice.service;

import com.jayphone.practice.config.FgcmProperties;
import com.jayphone.practice.entity.SendResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/5
 */
@Slf4j
@Component
public class Module4GServer {
//    private volatile static Module4GServer instance;
//
//    public static final int SERVER_PORT = 8234;
//

    @Resource
    private SocketThreadManager socketThreadManager;

    @Resource
    private FgcmProperties fgcmProperties;

    private ServerSocket serverSocket;

//    private Module4GServer() {
//        socketThreadManager = new SocketThreadManager();
//    }

//    public static Module4GServer getInstance() {
//        if (instance == null) {
//            synchronized (Module4GServer.class) {
//                if (instance == null) {
//                    instance = new Module4GServer();
//                }
//            }
//        }
//        return instance;
//    }

    public void start() {
        if (!fgcmProperties.getEnable()) {
            log.info("服务未启用");
            return;
        }
        try {
            serverSocket = new ServerSocket(8234);
            while (true) {
                Socket socket = serverSocket.accept();
                log.info("设备{} 接入连接", socket.getRemoteSocketAddress());
                SocketThread socketThread = new SocketThread(socket);
                socketThread.start();
                socketThreadManager.addSocketThread(socket.getInetAddress().getHostAddress(), socketThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public SendResult sendMsg(String phone, String msg) {
        if (!socketThreadManager.hasSocketThread()) {
            return null;
        }
        SocketThread socketThread = socketThreadManager.getSocketThread();
        if (!ObjectUtils.isEmpty(socketThread)) {
            return socketThread.sendMsg(phone, msg);
        } else {
            return null;
        }
    }

    public SendResult sendVoice(String phone, String msg) {
        if (!socketThreadManager.hasSocketThread()) {
            return null;
        }
        SocketThread socketThread = socketThreadManager.getSocketThread();
        if (!ObjectUtils.isEmpty(socketThread)) {
            return socketThread.sendVoice(phone, msg);
        } else {
            return null;
        }
    }

    public void shutdown() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socketThreadManager.closeSockets();
    }
}

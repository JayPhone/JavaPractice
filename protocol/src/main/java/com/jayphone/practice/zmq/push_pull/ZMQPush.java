package com.jayphone.practice.zmq.push_pull;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description 如果消息未被拉取，则会阻塞
 * @date 2023/9/11
 */
public class ZMQPush {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket pushSocket = context.createSocket(SocketType.PUSH);
        pushSocket.bind("tcp://*:5557");

        for (int i = 0; i < 100000; i++) {
            String content = "push content " + i;
            pushSocket.send(content);
            System.out.println("推送消息：" + content);
            Thread.sleep(1000);
        }
    }
}

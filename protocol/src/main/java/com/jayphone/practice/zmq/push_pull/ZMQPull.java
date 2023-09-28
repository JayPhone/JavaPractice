package com.jayphone.practice.zmq.push_pull;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description 拉取消息，拉取之后才能推送新消息
 * @date 2023/9/11
 */
public class ZMQPull {
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket pullSocket1 = context.createSocket(SocketType.PULL);
        ZMQ.Socket pullSocket2 = context.createSocket(SocketType.PULL);

        pullSocket1.connect("tcp://127.0.0.1:5557");
        pullSocket2.connect("tcp://127.0.0.1:5557");

        while (true) {
            String message1 = pullSocket1.recvStr();
            System.out.println("socket1拉取消息：" + message1);

            String message2 = pullSocket2.recvStr();
            System.out.println("socket2拉取消息：" + message2);
        }
    }
}

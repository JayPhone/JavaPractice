package com.jayphone.practice.zmq.pair;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description
 * @date 2023/9/11
 */
public class ZMQPairA {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.PAIR);
        socket.bind("tcp://*:5558");

        for (int i = 0; i < 100000; i++) {
            String content = "Content from Pair A " + i;
            socket.send(content);
            System.out.println("发送：" + content);

            String received = socket.recvStr();
            System.out.println("接收：" + received);

            Thread.sleep(1000);
        }
    }
}

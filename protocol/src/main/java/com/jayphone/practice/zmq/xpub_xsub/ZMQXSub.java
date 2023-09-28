package com.jayphone.practice.zmq.xpub_xsub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.nio.charset.StandardCharsets;

/**
 * @author JayPhone
 * @description
 * @date 2023/9/12
 */
public class ZMQXSub {
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket xSubSocket = context.createSocket(SocketType.XSUB);
        xSubSocket.connect("tcp://127.0.0.1:5560");
        xSubSocket.subscribe("topic1".getBytes(ZMQ.CHARSET));
        while (true) {
            String topic = xSubSocket.recvStr();
            String message = xSubSocket.recvStr();

            System.out.println("Received message for topic '" + topic + "': " + message);
        }
    }
}
